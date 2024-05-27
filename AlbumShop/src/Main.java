import Pages.*;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = null;
            try {
                homePage = new HomePage(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            homePage.setVisible(true);
        });
    }
}
