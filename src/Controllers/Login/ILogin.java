package Controllers.Login;

import javax.swing.*;

public interface ILogin {

    /**
     * Validate the email and password
     * @param txtEmail
     * @param txtPassword
     */
    public void validate(JTextField txtEmail, JPasswordField txtPassword);

    /**
     * Close the system
     */
    public void close();
}
