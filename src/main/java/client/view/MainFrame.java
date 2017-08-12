package client.view;


import client.view.base.LoginPanel;
import client.view.base.RequestDialog;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame of Distributed Pacman game
 *
 * Created by Chiara Varini on 30/06/17.
 */
public class MainFrame extends JFrame {

    public static final Dimension DIMENSION = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
    private static MainFrame SINGLETON = null;

    public static MainFrame getInstance(){
        if(SINGLETON == null){
            SINGLETON = new MainFrame();
        }
        return SINGLETON;
    }

    private MainFrame(){
        super();

        setSize(DIMENSION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new LoginPanel());
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        revalidate();
        repaint();
    }

    /**
     * Render the request to play a match
     * sent by another player
     * @param username
     */
    public void showRequest(final String username){
        RequestDialog requestDialog = new RequestDialog(this, username);
        requestDialog.setVisible(true);
    }
}