package Pages;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomePage extends JDialog{
    private JPanel homePanel;
    private JButton loginBtn;
    private JButton registBtn;
    private  JLabel kep;
    
    public HomePage(JFrame parent) throws IOException{
        super(parent);
        setTitle("Kezdőoldal");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

         File imageFile = new File("C:\\Progtech\\AlbumShop\\image.jpg");
        Image img = ImageIO.read(imageFile);
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        kep.setIcon(new ImageIcon(scaledImg));
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = null;
                try {
                    login = new Login(null);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                login.setVisible(true);
            }
        });

        registBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
                RegisterPage register = null;
                try {
                    register = new RegisterPage(null);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                register.setVisible(true);
            }
        });
    }
}
