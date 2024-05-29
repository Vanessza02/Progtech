package Pages;

import Albums.AlbumAbstract;
import classes.Album;
import classes.DeleteAlbumCommand;
import classes.RowListener;
import classes.TableRowClickListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumPage {
    public class AlbumsPage extends  JDialog {
        final String DB_URL = "jdbc:mysql://localhost/albumshop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        private JButton addEvent;
        private JPanel calendarPage;
        private JTable eventsTable;
        private JLabel titleText;
        private JButton backButton;
        private JButton deleteEventButton;
        private JScrollPane dataPanel;
        private AlbumAbstract albumAbstract;

        public AlbumsPage(JFrame parent, AlbumAbstract album) {
            super(parent);
            setTitle("Albumok");
            setContentPane(calendarPage);
            setMinimumSize(new Dimension(800, 600));
            setModal(true);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            albumAbstract = album;
            titleText.setText(albumAbstract.getTitle());

            ListAlbums(albumAbstract.getID());
            addAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    CreateAlbum createAlbumPage = new CreateAlbum(null, albumAbstract);
                    CreateAlbum.setVisible(true);
                }
            });
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    ListAlbums la = null;
                    try {
                        la = new ListAlbums(null);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    la.setVisible(true);
                }
            });
        }

        private void ListAlbums(int albumId) {

            albumId = albumId;

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();

                String sql = "SELECT ID, artist, title, genre, price FROM album WHERE user_id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, albumId);

                ResultSet rs = preparedStatement.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();

                DefaultTableModel model = (DefaultTableModel) eventsTable.getModel();
                String[] colName = new String[]{"Előadó", "Album", "Műfaj", "Ár"};
                model.setColumnIdentifiers(colName);

                eventsTable.revalidate();
                eventsTable.repaint();


                String artist, title, genre;
                int price;
                int id;
                List<Album> albums = new ArrayList<>();

                while (rs.next()) {
                    Album album = new Album();
                    id = rs.getInt("ID");
                    title = rs.getString("title");
                    artist = rs.getString("artist");
                    genre = rs.getString("genre");
                    price = rs.getInt("price");
                    Object[] row = {artist, title, genre, price};
                    model.addRow(row);

                    album.setArtist(artist);
                    album.setTitle(title);
                    album.setGenre(genre);
                    album.setPrice(price);
                    albums.add(album);
                }

                stmt.close();
                conn.close();

                RowListener rl = new RowListener();
                eventsTable.addMouseListener(new TableRowClickListener(eventsTable, rl));
                deleteEventButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = rl.GetSelectedRow();
                        try {
                            DeleteAlbum(albums.get(row));
                            dispose();
                            AlbumPage a = new AlbumPage(null, albumAbstract);
                            a.setVisible(true);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, "Nincs kiválasztva sor!");
                        }

                    }
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        private void DeleteAlbum(Album album) {
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();

                String sql = "DELETE FROM album WHERE ID = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, album.getAlbumID());

                preparedStatement.execute();

                DeleteAlbumCommand d = new DeleteAlbumCommand(albumAbstract, album);
                d.ExecuteAlbum();
                dataPanel.revalidate();
                eventsTable.revalidate();
                eventsTable.repaint();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
