package view;


import view.utils.ImagesResolutions;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame of Distributed Pacman game
 *
 * Created by chiaravarini on 30/06/17.
 */
public class MainFrame extends JFrame {

    public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static ImagesResolutions RESOLUTION = Utils.getResolution();
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
        setResizable(false);

        setContentPane(new GameStartPanel());
        setVisible(true);

    }

    public static void main(String[] args) {
        MainFrame.getInstance();
    }
}