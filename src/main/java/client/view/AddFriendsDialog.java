package client.view;

import controller.FakeController;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.FONT_SIZE;
import static client.view.utils.JComponentsUtils.createWhitePanel;

/**
 * Panel used to invite a player to your match
 * Created by chiaravarini on 13/07/17.
 */
public class AddFriendsDialog extends JDialog {

    private final JTextField username = new JTextField(FONT_SIZE);
    private final JButton send = new JButton("Send Request");
    private final JButton cancel = new JButton("Cancel");
    private final FakeController controller = new FakeController();

    private int friendsAdded = 0;

    public AddFriendsDialog(final JDialog parent){
        super(parent, "Add Friend", true);
        setSize(new Dimension(parent.getWidth()/2, parent.getHeight()/3));
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JPanel textFieldPanel = createWhitePanel();
        textFieldPanel.add(new JLabel("Insert friend username"));
        textFieldPanel.add(username);

        p.add(textFieldPanel);

        JPanel buttonPanel = createWhitePanel();
        buttonPanel.add(cancel);
        buttonPanel.add(send);
        p.add(buttonPanel);

        JLabel friendsAddedCounter = new JLabel("Firends added: 0");
        JPanel friendsAddedPanel = createWhitePanel();
        friendsAddedPanel.add(friendsAddedCounter);
        p.add(friendsAddedPanel);

        add(p);
        pack();
        cancel.addActionListener(e->dispose());
        send.addActionListener(e->{
            friendsAdded++;
            username.setText("");
            friendsAddedCounter.setText("Firends added: "+friendsAdded);
            controller.sendRequest(username.getText());
        });
    }
}
