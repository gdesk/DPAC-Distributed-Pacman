package client.view;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * This class represents the panel displayed when you lose
 * Created by chiaravarini on 26/07/17.
 */

public class GameOverDialog extends JDialog {

    public GameOverDialog(final JFrame frame){
        super(frame, "GAME OVER", true);
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() , (int) frameDim.getHeight() ));
            setLocationRelativeTo(null);
        }

        JLabel gameOver = new JLabel("GAME OVER");
        gameOver.setFont(new Font(gameOver.getFont().getName(), Font.BOLD, FONT_SIZE*10));
        gameOver.setForeground(Color.BLACK);

        JButton quit = new JButton("QUIT");
        quit.setFont(new Font(quit.getFont().getName(), Font.BOLD, FONT_SIZE*3));
        quit.setForeground(Color.BLACK);
        quit.addActionListener(e->{
            frame.setContentPane(new HomePanel("chia")); //TODO controller
            dispose();
        });

        setUndecorated(true);
        getContentPane().setBackground(new Color(150,150,150));
        setOpacity(new Float(0.7));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 50;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(gameOver, gbc);

        gbc.gridy = 1;
        add(quit, gbc);
    }
}
