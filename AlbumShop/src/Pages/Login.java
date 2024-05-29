package Pages;
import classes.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Login extends JDialog {

    private JTextField jelszotext;
    private JTextField usernametext;
    private JButton signbutt;
    private JLabel kep;
    private JPanel mainPanel;
    public static User user;

    public Login(JFrame parent) throws IOException {
        super(parent);
        setTitle("Login");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

        File imageFile = new File("C:\\Progtech\\AlbumShop\\image.jpg");
        Image img = ImageIO.read(imageFile);
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        kep.setIcon(new ImageIcon(scaledImg));

        signbutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernametext.getText();
                String password = String.valueOf(jelszotext.getText());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Login.this, "Az összes mezőt ki kell tölteni!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    user = getAuthenticatedUser(username, password);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (user != null) {
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Email or Password Invalid",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private User getAuthenticatedUser(String email, String password) throws IOException {
        final String DB_URL = "jdbc:mysql://localhost/albumshop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE username=? AND password=md5(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setID(resultSet.getInt(1));
                user.getEmail();
                user.getPassword();
            }

            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        this.dispose();
        ListAlbums laPage = new ListAlbums(null);
        laPage.setVisible(true);
        return user;
    }
}