package client.view.playground;

import client.view.MainFrame;

import javax.swing.*;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class MicroMapPanel extends JPanel {

    public MicroMapPanel(){
        setSize((int) MainFrame.DIMENSION.getWidth()/10, (int)MainFrame.DIMENSION.getHeight()/5);
        setBounds((int) MainFrame.DIMENSION.getWidth()-getWidth(), (int) MainFrame.DIMENSION.getHeight()-getHeight(),getWidth(),getHeight());

    }

}
