package client.view;

import client.view.utils.JComponentsUtils;

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

        // JPanel scores = new JPanel();

        JButton playAgain = JComponentsUtils.createBlackButton("PLAY AGAIN");
        playAgain.addActionListener(e->{
            //
            dispose();
        });
        JButton quit = JComponentsUtils.createBlackButton("QUIT");
        quit.addActionListener(e->{
            frame.setContentPane(new HomePanel("chia")); //TODO controller
            dispose();
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.fill = GridBagConstraints.NONE;
        add(playAgain, gbc);
        gbc.gridx = 1;
        add(new JLabel(" "), gbc);
        gbc.gridx = 2;
        add(quit, gbc);

        pack();

    }
}
