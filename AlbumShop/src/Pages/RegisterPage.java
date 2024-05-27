package Pages;
import classes.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Logger;

public class RegisterPage extends JDialog {

    final static Logger logger = Logger.getLogger(String.valueOf(RegisterPage.class));
    private JTextField emailtext;
    private JTextField jelszotext;
    private JTextField usernametext;
    private JButton regbutt;
    private JButton signinbutt;
    private JLabel kep;
    private JPanel mainPanel;
    public User user;

    public  RegisterPage(JFrame parent) throws IOException {

        super(parent);
        setTitle("Hozz létre egy új fiókot!");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        File imageFile = new File("C:\\Users\\janos\\Downloads\\Progtech-main\\AlbumShop\\image.jpg");
        Image img = ImageIO.read(imageFile);
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        kep.setIcon(new ImageIcon(scaledImg));

        regbutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Regisztráció gomb lenyomva.");
                registerUser();
            }
        });
        signinbutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    Login signInPage = new Login(null);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
    public void registerUser() {
        String Email = emailtext.getText();
        String Username = usernametext.getText();
        String Password = String.valueOf(jelszotext.getText());

        if (Email.isEmpty() || Username.isEmpty()  || Password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Az összes mezőt ki kell tölteni!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Email.contains("@")) {
            JOptionPane.showMessageDialog(this, "A beírt email nem valós!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String DB_URL = "jdbc:mysql://localhost/albumshop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO user(username, email, password) VALUES (?,?, md5(?))";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Email);
            preparedStatement.setString(3, Password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.setUsername(Username);
                user.setEmail(Email);
                user.setPassword(Password);
            }
            stm.close();
            conn.close();
        } catch (Exception exception) {
            logger.warning("Hiba történt: " + exception.getMessage());
        }


        if (user != null) {
            JOptionPane.showMessageDialog(this, "Sikeres regisztráció!");
            //SignInPages signInPage = new SignInPages(null);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sikertelen regisztráció!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
