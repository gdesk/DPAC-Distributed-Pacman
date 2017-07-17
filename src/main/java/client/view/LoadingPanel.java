package client.view;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.*;

/**
 * Panel displayed while loading all the game data
 * Created by chiaravarini on 14/07/17.
 */
public class LoadingPanel extends JPanel {

    public LoadingPanel(){
        setBackground(LOGIN_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        JPanel center = createBlackPanel();
        center.setLayout(new BorderLayout());

        JLabel gifLabel = new JLabel();
        gifLabel.setIcon(new ImageIcon(Utils.getGif(Res.LOADING_GIF())));

        JPanel labelPanel = createBlackPanel();
        labelPanel.setLayout(new GridBagLayout());
        JLabel loading = new JLabel("LOADING...");
        loading.setForeground(BACKGROUND_COLOR);
        loading.setFont(new Font(loading.getFont().getFontName(), Font.BOLD, FONT_SIZE));
        labelPanel.add(loading, gbc);

        center.add(gifLabel, BorderLayout.CENTER);
        center.add(labelPanel, BorderLayout.SOUTH);

        add(center, gbc);
    }
}
