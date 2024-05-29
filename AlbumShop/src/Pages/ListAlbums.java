package Pages;

import Albums.AlbumAbstract;
import classes.RowListener;
import classes.TableRowClickListener;
import classes.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ListAlbums extends JDialog {


    private JPanel ListAlbumsPanel;
    private JButton selectedAlbumBtn;
    private JTable albumTable;
    private User user;

    public ListAlbums(JFrame parent) throws IOException {
        super(parent);
        setTitle("Albumok");
        setContentPane(ListAlbumsPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);
        ListAllAlbums();
    }

    private void ListAllAlbums(){

        final String DB_URL = "jdbc:mysql://localhost/albumshop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        user = new User();
        user.setID(Login.user.getID());
        user.setEmail(Login.user.getEmail());
        user.setUsername(Login.user.getUsername());

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String query = "SELECT title, type, ID FROM `albumshop` WHERE user_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getID());

            ResultSet rs = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) albumTable.getModel();

            String[] colName = new String[]{"Előadó", "Cím", "Műfaj", "Ár"};
            model.setColumnIdentifiers(colName);

            String artist, title, genre;
            int id, price;
            List<AlbumAbstract> albums = new ArrayList<>();

            while (rs.next()) {
                artist = rs.getString("title");
                title = rs.getString("title");
                genre = rs.getString("genre");
                price = rs.getInt("price");
                id = rs.getInt("ID");

                Object[] row = {artist, title, genre, price};
                model.addRow(row);
            }
            stm.close();
            conn.close();

            RowListener rl = new RowListener();
            albumTable.addMouseListener(new TableRowClickListener(albumTable, rl));
            selectedAlbumBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = rl.GetSelectedRow();
                    try {
                        albums.get(row).addObserver(user);
                        AlbumPage albumPage = new AlbumPage(null, albums.get(row));
                        dispose();
                        AlbumPage.setVisible(true);
                    }
                    catch (Exception exception){
                        JOptionPane.showMessageDialog(null, "Nincs kiválasztva sor!");
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
