package client.view.match;

import client.model.PlayerImpl;
import client.view.MainFrame;
import client.view.base.HomePanel;
import client.view.utils.JComponentsUtils;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.FONT_SIZE;

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

        JPanel victoryPanel = JComponentsUtils.createTrasparentPanel();
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
        quit.addActionListener(e->MainFrame.getInstance().setContentPane(new HomePanel(PlayerImpl.username())));

        victoryPanel.add(winLabel,gbc);
        gbc.gridy = 1;
        victoryPanel.add(l, gbc);
        gbc.gridy = 2;
        victoryPanel.add(quit,gbc);
        add(victoryPanel, 0);

        setUndecorated(true);
        getContentPane().setBackground(BACKGOURND_COLOR);
        setOpacity(BACKGROUND_OPACITY.floatValue());
    }
}
