package Pages;

import classes.AddAlbumCommand;
import Albums.AlbumAbstract;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class CreateAlbum extends JDialog {
    private JPanel createAlbumPanel;
    private JButton createAlbumBtn;
    private JTextField artistText;
    private JTextField titleText;
    private JTextField genreText;
    private JTextField priceText;
    public AlbumAbstract albumAbstract;

    public CreateAlbum(JFrame parent, AlbumAbstract album) {
        super(parent);
        setTitle("Új album létrehozása");
        setContentPane(createAlbumPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        albumAbstract = album;

        createAlbumBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAlbumToDatabase(albumAbstract);
            }
        });
    }

    public void AddAlbumToDatabase(AlbumAbstract album) {
        if (album == null) {
            JOptionPane.showMessageDialog(this, "Album information is missing!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int user_id = album.getUser_id();
        String artist = artistText.getText();
        String title = titleText.getText();
        String genre = genreText.getText();
        int price = Integer.parseInt(priceText.getText());

        if (title.isEmpty() || artist.isEmpty() || genre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Az összes mezőt ki kell tölteni!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (price < 1000 || price > 20000) {
            JOptionPane.showMessageDialog(this, "Nem megfelelő az album ára!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String DB_URL = "jdbc:mysql://localhost/albumshop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO album(user_id, artist, title, genre, price) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, artist);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, genre);
            preparedStatement.setInt(5, price);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int albumID = generatedKeys.getInt(1);
                }
                album.setArtist(artist);
                album.setTitle(title);
                album.setGenre(genre);
                album.setPrice(price);
                AddAlbumCommand a = new AddAlbumCommand(album);
                a.ExecuteEvent();
            }
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dispose();
        ListAlbums la = null;
        try {
            la = new ListAlbums(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        la.setVisible(true);
    }
}