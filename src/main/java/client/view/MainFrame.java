package client.view;


import client.controller.BaseControllerUser;
import client.model.PlayerImpl;
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(new LoginPanel());
        setResizable(false);
        setVisible(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(!PlayerImpl.username().equals("")) {
                    BaseControllerUser.logout();
                }
                System.exit(0);
            }
        });
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