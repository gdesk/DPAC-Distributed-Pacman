package client.view.match;

import client.controller.BaseControllerUser;
import client.model.MatchImpl;
import client.model.MatchResult;
import client.model.MatchResultImpl;
import client.model.PlayerImpl;
import client.view.MainFrame;
import client.view.base.HomePanel;
import client.view.utils.JComponentsUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

import static client.view.utils.JComponentsUtils.FONT_SIZE;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

/**
 * Created by chiaravarini on 18/08/17.
 */
public class VictoryDialog extends JDialog {

    private static final Color BACKGOURND_COLOR = new Color(150,150,150);
    private static final Double BACKGROUND_OPACITY = 0.7;

    public VictoryDialog(final JFrame frame, final String result){
        super(frame,  true);
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() , (int) frameDim.getHeight() ));
            setLocationRelativeTo(null);
        }

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

        JButton quit = new JButton("QUIT");
        quit.setFont(new Font(quit.getFont().getName(), Font.BOLD, FONT_SIZE*3));
        quit.setForeground(Color.BLACK);
        quit.addActionListener(e->{
            MatchResult match = new MatchResultImpl();
            match.date_$eq(Calendar.getInstance());
            match.result_$eq(true);
            match.score_$eq(MatchImpl.myCharacter().score());
            BaseControllerUser.saveMatch(match);
            MainFrame.getInstance().setContentPane(new HomePanel(PlayerImpl.username()));
            dispose();
        });

        victoryPanel.add(winLabel,gbc);
        gbc.gridy = 1;
        victoryPanel.add(l, gbc);
        gbc.gridy = 2;
        victoryPanel.add(quit,gbc);
        add(victoryPanel, 0);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if(gd.isWindowTranslucencySupported(TRANSLUCENT)) {
            setUndecorated(true);
            setOpacity(BACKGROUND_OPACITY.floatValue());
        }
        getContentPane().setBackground(BACKGOURND_COLOR);
    }
}
