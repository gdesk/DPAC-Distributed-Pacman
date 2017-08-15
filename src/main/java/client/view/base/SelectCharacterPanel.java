package client.view.base;

import client.controller.BaseControllerMatch;
import client.controller.BaseControllerUser;
import client.view.MainFrame;
import client.view.Utils;
import client.view.utils.JComponentsUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static client.view.utils.JComponentsUtils.*;

/**
 * Concrete class that implements SelectCharacterView interface
 * Created by Chiara Varini on 14/07/17.
 */
public class SelectCharacterPanel extends JPanel implements SelectCharacterView{

    private final Dimension CHARACTER_IMAGE_DIMENSION = calculatedImageCharDimension(10);
    private final Dimension PLAYGROUND_IMAGE_DIMENSION = calculatedImageCharDimension(2.2);

    private final JButton doneButton = createButton("DONE");

    private JButton characterChoosed = new JButton();
    private boolean isCharacterChoosed = false;
    private JButton playgroundChoosed = new JButton();
    private boolean isPlaygroundChoosed = false;

    private final List<JButton> charactersButton = new ArrayList<>();
    private final List<JButton> playgroundsButton = new ArrayList<>();

    public SelectCharacterPanel(){
        setLayout(new BorderLayout());

        JPanel buttonPanel = createBlackPanel();
        doneButton.setEnabled(false);
        JButton exitButton = createButton("EXIT");
        buttonPanel.add(exitButton);
        buttonPanel.add(doneButton);
        JPanel north = createBlackPanel();
        north.setLayout(new BorderLayout());
        north.add(buttonPanel, BorderLayout.EAST);

        JPanel center = createBlackPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JPanel characterPanel = JComponentsUtils.createBackgroundColorPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.X_AXIS));

        Utils.getJavaMap(BaseControllerMatch.getCharacters()).forEach((name, image) -> {
            characterPanel.add(createImagePanel(image, name, CHARACTER_IMAGE_DIMENSION));
        });
        JScrollPane characterScroll = new JScrollPane(characterPanel);

        center.add(createSectionTitle("Select one Character"));
        center.add(characterScroll);

        JPanel south = createBlackPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));

        JPanel playgroundPanel = JComponentsUtils.createBackgroundColorPanel();
        playgroundPanel.setLayout(new BoxLayout(playgroundPanel, BoxLayout.X_AXIS));

        Utils.getJavaMap(BaseControllerMatch.getPlaygrounds()).forEach((index,image) -> {
            playgroundPanel.add(createImagePanel(image, index.toString(), PLAYGROUND_IMAGE_DIMENSION));
        });

        JScrollPane playgroundScroll = new JScrollPane(playgroundPanel);

        south.add(createSectionTitle("Select one Playground"));
        south.add(playgroundScroll);

        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        exitButton.addActionListener(e->{
            MainFrame.getInstance().setContentPane(new HomePanel(BaseControllerUser.player().username()));
        });

        doneButton.addActionListener(e->{
            LoadingPanel loadingPanel = new LoadingPanel();
            MainFrame.getInstance().setContentPane(loadingPanel);
            BaseControllerMatch.setLoadingView(loadingPanel);
            BaseControllerMatch.chosenCharacter(((ImageIcon)characterChoosed.getIcon()).getDescription());
            BaseControllerMatch.chosenPlayground(Integer.parseInt(((ImageIcon)playgroundChoosed.getIcon()).getDescription()));
            BaseControllerMatch.startMatch();
        });
    }

    @Override
    public void disableCharacter(final String nameImage){
        modifyStatusButton(false, nameImage);
    }

    @Override
    public void enableCharacter(final String nameImage){
        modifyStatusButton(true, nameImage);
    }

    private JPanel createImagePanel(final Image image, final String str, final Dimension dim){
        JPanel iconPanel = JComponentsUtils.createBackgroundColorPanel();
        int iconPadding = (int)dim.getWidth()/5;
        iconPanel.setBorder(BorderFactory.createEmptyBorder(iconPadding,iconPadding,iconPadding,iconPadding));

        JButton imageButton = new JButton();
        imageButton.setBorder(BorderFactory.createLineBorder(Color.black));

        ImageIcon icon = new ImageIcon(Utils.getScaledImage(image, (int)dim.getWidth(), (int)dim.getHeight()));
        icon.setDescription(str);
        imageButton.setIcon(icon);
        iconPanel.add(imageButton);
        if(imageButton.getIcon().getIconWidth() == CHARACTER_IMAGE_DIMENSION.getWidth()){
            charactersButton.add(imageButton);
        } else {
            playgroundsButton.add(imageButton);
        }
        addActionListenerToButton(imageButton);

        return iconPanel;
    }

    private void addActionListenerToButton(final JButton button){
        if(button.getIcon().getIconWidth() == CHARACTER_IMAGE_DIMENSION.getWidth()){
            button.addActionListener(e-> {
                if (!isCharacterChoosed){
                    //this.characterChoosed.setEnabled(true);
                    this.characterChoosed = button;
                    this.isCharacterChoosed = true;
                    if (BaseControllerMatch.chosenCharacter(((ImageIcon) button.getIcon()).getDescription())) {
                        charactersButton.forEach(c -> c.setEnabled(false));
                        button.setEnabled(true);
                    }
                    checkDone();
                }
            });

        } else {
            button.addActionListener(e-> {
                if (!isPlaygroundChoosed) {
                    //this.playgroundChoosed.setEnabled(true);
                    this.playgroundChoosed = button;
                    this.isPlaygroundChoosed = true;
                    playgroundsButton.forEach(c -> c.setEnabled(false));
                    button.setEnabled(true);
                    checkDone();
                }
            });
        }
    }

    private Dimension calculatedImageCharDimension(final double divider){
        Dimension frameDimension = MainFrame.getInstance().getSize();
        int dimention = frameDimension.getWidth()<frameDimension.getHeight() ?
                (int)(frameDimension.getWidth()/divider) :
                (int)(frameDimension.getHeight()/divider);

        return new Dimension(dimention,dimention);
    }

    private void checkDone(){
        if(isCharacterChoosed && isPlaygroundChoosed){
            doneButton.setEnabled(true);
        }
    }

    private void  modifyStatusButton(final boolean status, final String nameImage){
        charactersButton.forEach(b->{
            if( ((ImageIcon)b.getIcon()).getDescription().equals(nameImage)){
                b.setEnabled(status);
            }
        });
    }
}
