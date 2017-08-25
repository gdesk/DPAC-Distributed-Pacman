package client.view.match;

import client.controller.BaseControllerUser;
import client.model.MatchImpl;
import client.model.MatchResult;
import client.model.MatchResultImpl;
import client.model.PlayerImpl;
import client.view.MainFrame;
import client.view.base.HomePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

import static client.view.utils.JComponentsUtils.FONT_SIZE;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

/**
 * This class represent the panel displayed when the game finish
 * Created by Chiara Varini on 25/08/17.
 */
public class EndGameDialog extends JDialog {

    private static final Color BACKGOURND_COLOR = new Color(150,150,150);
    private static final Double BACKGROUND_OPACITY = 0.7;

    public EndGameDialog(final JFrame frame, final String title){

        super(frame, title, true);
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth(), (int) frameDim.getHeight()));
            setLocationRelativeTo(null);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            if(gd.isWindowTranslucencySupported(TRANSLUCENT)) {
                setUndecorated(true);
                setOpacity(BACKGROUND_OPACITY.floatValue());
            }
            getContentPane().setBackground(BACKGOURND_COLOR);
        }
    }

    /**
     * @return The quit button
     */
    public JButton getQuitButton(){
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
        return quit;
    }
}
