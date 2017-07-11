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
    //public static final Controller controller = Controller.getInstance();
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
        setContentPane(new LoginPanel());
        setResizable(false);
        setVisible(true);
    }

    /*public void setPlayground(final PlaygroundPanel playground){
        setContentPane(new GameStartPanel(playground));
        setVisible(true);
    }*/

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        revalidate();
        repaint();
    }
}