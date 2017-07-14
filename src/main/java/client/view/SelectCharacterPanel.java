package client.view;

import client.view.utils.ImagesUtils;
import controller.FakeController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chiaravarini on 14/07/17.
 */
public class SelectCharacterPanel extends JPanel {

    private final FakeController controller = new FakeController();

    public SelectCharacterPanel(){

        setLayout(new BorderLayout());

        JPanel characterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        controller.getAllCharactersImages().forEach(img-> {
            characterPanel.add(createImagePanel(img, new Dimension(100,100)), gbc);
            gbc.gridx++;
        });
        JScrollPane characterScroll = new JScrollPane(characterPanel);

        JPanel playgroundPanel = new JPanel();
        playgroundPanel.setBorder(BorderFactory.createEmptyBorder());
        playgroundPanel.setLayout(new BoxLayout(playgroundPanel, BoxLayout.X_AXIS));

        controller.getAllPlaygroundsImages().forEach( img -> {
            playgroundPanel.add(createImagePanel(img, new Dimension(350,350)));
        });
        JScrollPane playgroundScroll = new JScrollPane(playgroundPanel);

        add(characterScroll, BorderLayout.NORTH);
        add(playgroundScroll, BorderLayout.SOUTH);

    }

    private JPanel createImagePanel(final Image image, final Dimension dim){
        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(ImagesUtils.getScaledImage(image, (int)dim.getWidth(), (int)dim.getHeight()));
        imageLabel.setIcon(icon);
        imagePanel.add(imageLabel);
        return imagePanel;
    }

}



       /* PlaygroundPanel playgroundView = controller.initializePlaygroundView( null );//model.getCharacterList());
        MainFrame.getInstance().setContentPane(playgroundView);

        UserInputController keyboardController = new UserInputController(playgroundView);
        playgroundView.addKeyListener(keyboardController);*/