package client.view;

import client.controller.BaseControllerMatch;
import client.controller.BaseControllerUser;
import client.controller.ControllerMatch;
import client.model.Direction;
import client.model.Playground;
import client.view.utils.ImagesUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import static client.view.utils.JComponentsUtils.*;

/**
 * Created by chiaravarini on 14/07/17.
 */
public class SelectCharacterPanel extends JPanel implements SelectCharacterView{

    private final Dimension CHARACTER_IMAGE_DIMENSION = calculatedImageCharDimension(10);//new Dimension(100,100);
    private final Dimension PLAYGROUND_IMAGE_DIMENSION = calculatedImageCharDimension(2.2);

    private final ControllerMatch controller = BaseControllerMatch.instance(this) ;
    private final JButton doneButton = createButton("DONE");;

    private JButton characterChoosed = new JButton();
    private boolean isCharacterChoosed = false;
    private JButton playgroundChoosed = new JButton();;
    private boolean isPlaygroundChoosed = false;

    private final List<JButton> characterButton = new ArrayList<>();

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

        JPanel characterPanel = createColorPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.X_AXIS));

        Utils.getJavaMap(controller.getCharacters()).forEach((name,image) -> {
            characterPanel.add(createImagePanel(image, name, CHARACTER_IMAGE_DIMENSION));
        });
        JScrollPane characterScroll = new JScrollPane(characterPanel);

        center.add(createSectionTitle("Select one Character"));
        center.add(characterScroll);

        JPanel south = createBlackPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));

        JPanel playgroundPanel = createColorPanel();
        playgroundPanel.setLayout(new BoxLayout(playgroundPanel, BoxLayout.X_AXIS));

        Utils.getJavaMap(controller.getPlaygrounds()).forEach((index,image) -> {
            playgroundPanel.add(createImagePanel(image, index.toString(), PLAYGROUND_IMAGE_DIMENSION));
        });

        JScrollPane playgroundScroll = new JScrollPane(playgroundPanel);

        south.add(createSectionTitle("Select one Playground"));
        south.add(playgroundScroll);

        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        exitButton.addActionListener(e->{
            MainFrame.getInstance().setContentPane(new HomePanel(BaseControllerUser.instance().getPlayerUsername()));
        });

        doneButton.addActionListener(e->{
            MainFrame.getInstance().setContentPane(new LoadingPanel());
            controller.chooseCharacter(((ImageIcon)characterChoosed.getIcon()).getDescription());
            controller.choosePlayground(Integer.parseInt(((ImageIcon)playgroundChoosed.getIcon()).getDescription()));
        });
    }

    private JPanel createImagePanel(final Image image, final String str, final Dimension dim){
        JPanel iconPanel = createColorPanel();
        int iconPadding = (int)dim.getWidth()/5;
        iconPanel.setBorder(BorderFactory.createEmptyBorder(iconPadding,iconPadding,iconPadding,iconPadding));
        JButton imageButton = new JButton();
        imageButton.setBorder(BorderFactory.createLineBorder(Color.black));
        imageButton.addActionListener(e->{
            imageButton.setEnabled(false);
            if(imageButton.getIcon().getIconWidth() == CHARACTER_IMAGE_DIMENSION.getWidth()){
                this.characterChoosed.setEnabled(true);
                this.characterChoosed = imageButton;
                this.isCharacterChoosed = true;
                controller.chooseCharacter(((ImageIcon)imageButton.getIcon()).getDescription());
            } else {
                this.playgroundChoosed.setEnabled(true);
                this.playgroundChoosed = imageButton;
                this.isPlaygroundChoosed = true;
            }
            checkDone();
        });

        ImageIcon icon = new ImageIcon(ImagesUtils.getScaledImage(image, (int)dim.getWidth(), (int)dim.getHeight()));
        icon.setDescription(str);
        imageButton.setIcon(icon);
        iconPanel.add(imageButton);
        characterButton.add(imageButton);
        return iconPanel;
    }

    private JLabel createSectionTitle(final String nameTitle){
        JLabel title = new JLabel(nameTitle);
        title.setForeground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(),Font.PLAIN, FONT_SIZE));
        return title;
    }

    private JPanel createColorPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        return panel;
    }

    private JButton createButton(final String name){
        JButton button = new JButton(name);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFont(new Font(getFont().getName(), Font.BOLD, FONT_SIZE));
        return button;
    }

    private Dimension calculatedImageCharDimension(final double divider){

        Dimension frameDimension = MainFrame.getInstance().getSize();
        if(frameDimension.getWidth()<frameDimension.getHeight()){
            return new Dimension((int)(frameDimension.getWidth()/divider), (int)(frameDimension.getWidth()/divider));
        } else {
            return new Dimension((int)(frameDimension.getHeight()/divider), (int)(frameDimension.getHeight()/divider));
        }
    }

    private void checkDone(){
        if(isCharacterChoosed && isPlaygroundChoosed){
            doneButton.setEnabled(true);
        }
    }

    @Override
    public void disableCharacter(final String nameImage){
        modifyStatusButton(false, nameImage);
    }

    @Override
    public void enableCharacter(final String nameImage){
        modifyStatusButton(true, nameImage);
    }

    @Override
    public void charactersChoosen(Map<String, Map<Direction, Image>> charactersMap) {

    }

    @Override
    public void playgroundChoosen(Playground playground) {

    }

    private void  modifyStatusButton(final boolean status, final String nameImage){
        characterButton.forEach(b->{
            if( ((ImageIcon)b.getIcon()).getDescription().equals(nameImage)){
                b.setEnabled(status);
            }
        });
    }
}
