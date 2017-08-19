package client.view.match;

import client.controller.BaseControllerUser;
import client.model.MatchImpl;
import client.model.MatchResult;
import client.model.MatchResultImpl;
import client.model.PlayerImpl;
import client.view.base.HomePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

import static client.view.utils.JComponentsUtils.FONT_SIZE;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

/**
 * This class represents the panel displayed when you lose
 * in wich you can return to your home
 * Created by Chiara Varini on 26/07/17.
 */

public class GameOverDialog extends JDialog {

    private static final Color BACKGOURND_COLOR = new Color(150,150,150);
    private static final Double BACKGROUND_OPACITY = 0.7;

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
            MatchResult match = new MatchResultImpl();
            match.date_$eq(Calendar.getInstance());
            match.result_$eq(false);
            match.score_$eq(MatchImpl.myCharacter().score());
            BaseControllerUser.sevaMatch(match);
            frame.setContentPane(new HomePanel(PlayerImpl.username()));
            dispose();
        });


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

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if(gd.isWindowTranslucencySupported(TRANSLUCENT)) {
            setUndecorated(true);
            setOpacity(BACKGROUND_OPACITY.floatValue());
        }

        getContentPane().setBackground(BACKGOURND_COLOR);
    }
}
