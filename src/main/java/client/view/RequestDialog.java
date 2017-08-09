package client.view;

import client.controller.BaseControllerMatch;
import client.controller.ControllerMatch;
import client.view.utils.JComponentsUtils;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.FONT_SIZE;
import static client.view.utils.JComponentsUtils.createBackgroundColorPanel;

/**
 * This class presents the panel to be displayed when a match request is recieved
 * Created by chiaravarini on 30/07/17.
 */
public class RequestDialog extends JDialog {

    private final SelectCharacterPanel nextView = new SelectCharacterPanel();
    private final ControllerMatch controller = BaseControllerMatch.instance();

    public RequestDialog(final JFrame frame){

        super(frame, "Create Team", true);

        controller.view_$eq(nextView);

        JPanel p = createBackgroundColorPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 3));
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JPanel buttonPanel = JComponentsUtils.createBackgroundColorPanel();
            JButton yes = JComponentsUtils.createBlackButton("YES");
            yes.addActionListener(e->{
                //controller.sendResponse(true);
                frame.setContentPane(nextView);
                dispose();
            });

            JButton no = JComponentsUtils.createBlackButton("NO");
            no.addActionListener(e->{
                //controller.sendResponse(false);
                frame.setContentPane(nextView);
                dispose();
            });

            buttonPanel.add(yes);
            buttonPanel.add(no);

            JPanel labelPanel = JComponentsUtils.createBackgroundColorPanel();
            JLabel request = new JLabel("<html>DO YOU WANT<br> TO PLAY A MATCH?<html>");
            request.setFont(new Font(request.getFont().getName(), Font.BOLD, FONT_SIZE*2));
            labelPanel.add(request);
            add(labelPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }
}
