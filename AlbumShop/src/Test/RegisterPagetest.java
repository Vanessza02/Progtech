package Test;

import Pages.RegisterPage;
import junit.framework.TestCase;

import java.io.IOException;

public class RegisterPagetest extends TestCase {

    public void testRegisterUser_WithValidData() throws IOException {
        RegisterPage registerPage = new RegisterPage(null);
        registerPage.emailtext.setText("something@something.com");
        registerPage.usernametext.setText("user");
        registerPage.jelszotext.setText("password");


        registerPage.registerUser();

        assertNotNull(registerPage.user);
        assertEquals("user", registerPage.user.getUsername());
        assertEquals("something@something.com", registerPage.user.getEmail());
        assertEquals("password", registerPage.user.getPassword());
    }

    public void testRegisterUser_WithMissingFields() throws IOException {
        RegisterPage registerPage = new RegisterPage(null);
        registerPage.emailtext.setText("");
        registerPage.usernametext.setText("user");
        registerPage.jelszotext.setText("password");

        registerPage.registerUser();

        assertNull(registerPage.user);
    }

    //Nem tartalmaz @-jelet!
    public void testRegisterUser_WithWrongEmail() throws IOException {
        RegisterPage registerPage = new RegisterPage(null);
        registerPage.emailtext.setText("user");
        registerPage.usernametext.setText("user");
        registerPage.jelszotext.setText("password");


        registerPage.registerUser();

        assertNull(registerPage.user);
    }

}
