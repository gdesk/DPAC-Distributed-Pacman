package client.view.playground;

import client.view.MainFrame;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.BACKGROUND_COLOR;
import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class GamePanel extends JLayeredPane {

    public GamePanel(){
      setLayout(new BorderLayout());
    }

    public void addPlayground(final PlaygroundView playground){

    }

    public void showResult(final String result){
        add(MainFrame.getInstance().getContentPane(), 0);

        JPanel victoryPanel = new JPanel(new GridBagLayout());
        victoryPanel.setBounds(0, 0,(int) MainFrame.DIMENSION.getWidth(), (int) MainFrame.DIMENSION.getHeight());
        victoryPanel.setBackground(new Color(0,0,0,0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel l = new JLabel(result);
        l.setForeground(BACKGROUND_COLOR);
        l.setFont(new Font(l.getFont().getName(), Font.BOLD, FONT_SIZE*3));

        victoryPanel.add(l, gbc);
        add(victoryPanel, 1);
        revalidate();
        repaint();
    }

}
