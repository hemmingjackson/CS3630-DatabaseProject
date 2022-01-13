package Frames;

import Controllers.Login.ILogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UILogin extends JFrame {
    private JButton loginButton;
    private JPanel mainPanel;
    private JPasswordField passwordField;
    private JTextField emailField;

    private ILogin inter;

    public UILogin(ILogin inter) {
        initPanel();
        this.setVisible(true);
        this.setTitle("LOGIN");
        setLocationRelativeTo(null);

        this.inter = inter;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inter.validate(emailField, passwordField);
            }
        });
    }

    private void initPanel() {
        setContentPane(mainPanel);
        setSize(500, 400);
    }




}
