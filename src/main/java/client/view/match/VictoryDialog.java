package client.view.match;

import client.view.MainFrame;
import client.view.utils.JComponentsUtils;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * This class represents the panel displayed when you win
 * in wich you can return to your home
 * Created by Chiara Varini on 18/08/17.
 */
public class VictoryDialog extends EndGameDialog {

    public VictoryDialog(final JFrame frame, final String result){

       super(frame, "VICTORY");

        JPanel victoryPanel = JComponentsUtils.createTransparentPanel();
        victoryPanel.setSize(MainFrame.DIMENSION);
        victoryPanel.setLayout(new GridBagLayout());
        victoryPanel.setBounds(0, 0, (int) MainFrame.DIMENSION.getWidth(), (int) MainFrame.DIMENSION.getHeight());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel l = new JLabel("SCORE: " + result);
        l.setFont(new Font(l.getFont().getName(), Font.BOLD, FONT_SIZE*3));

        JLabel winLabel = new JLabel("YOU WIN");
        winLabel.setFont(new Font(winLabel.getFont().getName(), Font.BOLD, FONT_SIZE*10));

        victoryPanel.add(winLabel,gbc);
        gbc.gridy = 1;
        victoryPanel.add(l, gbc);
        gbc.gridy = 2;
        victoryPanel.add(getQuitButton(),gbc);
        add(victoryPanel, 0);
    }
}
