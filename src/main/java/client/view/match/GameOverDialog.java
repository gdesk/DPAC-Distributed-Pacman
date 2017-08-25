package client.view.match;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * This class represents the panel displayed when you lose
 * in wich you can return to your home
 * Created by Chiara Varini on 26/07/17.
 */

public class GameOverDialog extends EndGameDialog{

    public GameOverDialog(final JFrame frame){

        super(frame, "GAME OVER");

        JLabel gameOver = new JLabel("GAME OVER");
        gameOver.setFont(new Font(gameOver.getFont().getName(), Font.BOLD, FONT_SIZE*10));
        gameOver.setForeground(Color.BLACK);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 50;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(gameOver, gbc);

        gbc.gridy = 1;
        add(getQuitButton(), gbc);

    }
}
