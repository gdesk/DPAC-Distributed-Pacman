package client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chiaravarini on 11/07/17.
 */
public class LoginPanel extends JPanel {

    private final JTextField userInput = new JTextField(20);
    private final JTextField passwordInput = new JTextField(20);
    private final JButton login = new JButton("Login");
    private final JButton registration = new JButton("<html><u>registration<u><html>");

    LoginPanel(){
        // TODO setSize();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Username"), gbc);

        gbc.gridx = 1;
        gbc.ipady = 0;
        add(userInput);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        add(passwordInput, gbc);

        gbc.ipady = 10;
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(login, gbc);

        gbc.gridy = 3;
        add(registration, gbc);
    }

}


