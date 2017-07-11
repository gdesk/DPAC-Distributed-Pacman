package client.view;

import client.model.MatchResult;
import controller.FakeController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by chiaravarini on 11/07/17.
 */
public class LoginPanel extends JPanel {

    private final static Color BACKGROUND_COLOR = Color.black;
    private final static int JTEXTFIELD_LENGHT = 20;

    private final JTextField userInput = new JTextField(JTEXTFIELD_LENGHT);
    private final JTextField passwordInput = new JTextField(JTEXTFIELD_LENGHT);
    private final JButton login = new JButton("Login");
    private final JButton registration = new JButton("<html><u>registration<u><html>");

    public LoginPanel() {

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        //TODO resize gif
        JLabel label = new JLabel(new ImageIcon(Utils.getGif("login")));


        add(label, BorderLayout.CENTER);

        JPanel east = new JPanel(new GridBagLayout());
        east.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = JTEXTFIELD_LENGHT;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        JLabel username = new JLabel("Username");
        username.setForeground(Color.white);
        east.add(username, gbc);

        gbc.gridx = 1;
        gbc.ipady = 0;
        east.add(userInput);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel password = new JLabel("Password");
        password.setForeground(Color.white);
        east.add(password, gbc);

        gbc.gridx = 1;
        east.add(passwordInput, gbc);

        gbc.ipady = JTEXTFIELD_LENGHT/2;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        east.add(login, gbc);

        gbc.gridy = 3;
        east.add(registration, gbc);

        login.addActionListener(e->{
            //controller.login();
            List<MatchResult> matches = FakeController.getmatches(); //controller.getMathces(username); --> Return a List of matches
            MainFrame.getInstance().setContentPane(new HomePanel(matches));

        });

        registration.addActionListener(e->{
            //controller.registration();
        });

        add(east, BorderLayout.EAST);
    }
}


