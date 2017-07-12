import client.view.MainFrame;

import javax.swing.*;

/**
 * Created by ManuBottax on 16/06/2017.
 */
public class HelloPacMan {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance();   //TODO passare rif controller
            }
        });
    }
}
