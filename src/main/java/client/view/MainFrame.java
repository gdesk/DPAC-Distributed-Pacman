package client.view;


import javax.swing.*;
import java.awt.*;

/**
 * MainFrame of Distributed Pacman game
 *
 * Created by chiaravarini on 30/06/17.
 */
public class MainFrame extends JFrame {

    public static final Dimension DIMENSION = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
    private static MainFrame SINGLETON = null;

    public static MainFrame getInstance(){  //TODO passare il controller
        if(SINGLETON == null){
            SINGLETON = new MainFrame();
        }
        return SINGLETON;
    }

    private MainFrame(){
        super();

        setSize(DIMENSION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
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
}