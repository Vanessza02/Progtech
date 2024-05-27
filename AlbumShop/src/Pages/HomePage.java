package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JDialog{
    private JPanel homePanel;
    private JButton loginBtn;
    private JButton registBtn;

    public HomePage(JFrame parent){
        super(parent);
        setTitle("Kezdőoldal");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
                login.showPage();
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
