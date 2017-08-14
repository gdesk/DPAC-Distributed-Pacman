package client.view.match;

import client.controller.BaseControllerMatch;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.FONT_SIZE;
import static client.view.utils.JComponentsUtils.createBackgroundColorPanel;

/**
 * Panel used to invite a player to your match
 * Created by Chiara Varini on 13/07/17.
 */
public class AddFriendsDialog extends JDialog {

    private final JTextField username = new JTextField(FONT_SIZE);
    private int friendsAdded = 0;

    public AddFriendsDialog(final JDialog parent){
        super(parent, "Add Friend", true);
        setSize(new Dimension(parent.getWidth()/2, parent.getHeight()/3));
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JPanel textFieldPanel = createBackgroundColorPanel();
        textFieldPanel.add(new JLabel("Insert friend username"));
        textFieldPanel.add(username);

        p.add(textFieldPanel);

        JPanel buttonPanel = createBackgroundColorPanel();
        JButton send = new JButton("Send Request");
        JButton cancel = new JButton("Cancel");
        buttonPanel.add(cancel);
        buttonPanel.add(send);
        p.add(buttonPanel);

        JLabel friendsAddedCounter = new JLabel("Firends added: 0");
        JPanel friendsAddedPanel = createBackgroundColorPanel();
        friendsAddedPanel.add(friendsAddedCounter);
        p.add(friendsAddedPanel);

        add(p);
        pack();

        cancel.addActionListener(e->dispose());

        send.addActionListener(e->{
            friendsAdded++;
            username.setText("");
            friendsAddedCounter.setText("Firends added: "+friendsAdded);
            BaseControllerMatch.sendRequestAt(username.getText());
        });
    }
}
