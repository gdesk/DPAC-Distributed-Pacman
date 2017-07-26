package client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chiaravarini on 26/07/17.
 */
public class GameOverDialog extends JDialog {

    public GameOverDialog(final JFrame frame){
        super(frame, "GAME OVER", true);
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 3));
            setLocationRelativeTo(null);
        }

        JButton playAgain = new JButton("PLAY AGAIN");
        JButton quit = new JButton("QUIT");
        quit.addActionListener(e->frame.setContentPane(new HomePanel("chia")));//TODO lo passo dal controller


        JPanel scores = new JPanel();

        JPanel buttuonPanel = new JPanel();
        buttuonPanel.setLayout(new BoxLayout(buttuonPanel, BoxLayout.X_AXIS));
      //  buttuonPanel.add()
    }
}
