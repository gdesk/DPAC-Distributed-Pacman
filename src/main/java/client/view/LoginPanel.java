package client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that allows you to login and register a user
 * Created by chiaravarini on 11/07/17.
 */
public class LoginPanel extends JPanel {

    private final static Color BACKGROUND_COLOR = Color.black;
    private final static int JTEXTEFIELD_COLUMNS = 20;



    public LoginPanel() {

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        final JTextField userInput = new JTextField(JTEXTEFIELD_COLUMNS);
        final JTextField passwordInput = new JTextField(JTEXTEFIELD_COLUMNS);
        final JButton login = new JButton("Login");
        final JButton registration = new JButton("<html><u>registration<u><html>");

        //TODO resize gif

        JLabel label = new JLabel(new ImageIcon(Utils.getGif("login")));
        add(label, BorderLayout.CENTER);

        JPanel east = new JPanel(new GridBagLayout());
        east.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = JTEXTEFIELD_COLUMNS;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        JLabel username = new JLabel("Username");
        username.setForeground(Color.white);
        east.add(username, gbc);

        gbc.gridx = 1;
        gbc.ipady = 0;

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel password = new JLabel("Password");
        password.setForeground(Color.white);
        east.add(password, gbc);

        gbc.gridx = 1;
        east.add(passwordInput, gbc);

        gbc.ipady = JTEXTEFIELD_COLUMNS/2;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        east.add(login, gbc);

        gbc.gridy = 3;
        east.add(registration, gbc);

        east.add(userInput);

        gbc.gridx = 1;
        gbc.gridy = 4;
        final JLabel userErr = new JLabel("Wrong credential");
        userErr.setVisible(false);
        userErr.setForeground(Color.RED);
        east.add(userErr, gbc);

        login.addActionListener(e->{
            //boolean loginCorrect = controller.login();
            //if(!loginCorrect){
            if(userInput.getText().equals("")){
                userErr.setVisible(true);
            }else {
                MainFrame.getInstance().setContentPane(new HomePanel(userInput.getText()));
            }

        });

        registration.addActionListener(e->{
            RegistrationPanel registrationPanel = new RegistrationPanel(MainFrame.getInstance());
            registrationPanel.setVisible(true);
        });

        add(east, BorderLayout.EAST);
    }
}


