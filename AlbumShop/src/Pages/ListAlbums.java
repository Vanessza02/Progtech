package Pages;

import classes.RowListener;
import classes.TableRowClickListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

import static Pages.Login.user;

public class ListAlbums extends JDialog {
    private JPanel ListAlbumsPanel;
    private JTable albumTable;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JButton addAlbum;
    private AlbumAbstract albumAbstract;

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

    private void ListAllAlbums() {
        final String DB_URL = "jdbc:mysql://localhost/albumshop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT ID, artist, title, genre, price FROM album WHERE user_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getID());

            ResultSet rs = preparedStatement.executeQuery();
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Előadó", "Cím", "Műfaj", "Ár"}, 0);
            albumTable.setModel(model);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String artist = rs.getString("artist");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int price = rs.getInt("price");

                Object[] row = {id, artist, title, genre, price};
                model.addRow(row);
            }

            RowListener rl = new RowListener();
            albumTable.addMouseListener(new TableRowClickListener(albumTable, rl));
            addAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = albumTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int albumID = (int) albumTable.getValueAt(selectedRow, 0);
                        ((DefaultTableModel) albumTable.getModel()).removeRow(selectedRow);
                        try {
                            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                            String deleteQuery = "DELETE FROM album WHERE ID = ?";
                            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
                            deleteStatement.setInt(1, albumID);
                            deleteStatement.executeUpdate();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(ListAlbums.this, "Error deleting album.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ListAlbums.this, "No album selected.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            updateBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
             addAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CreateAlbum createAlbumDialog = new CreateAlbum(null,albumAbstract);
                    createAlbumDialog.setVisible(true);
                }
            });
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
