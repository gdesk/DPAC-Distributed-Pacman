package client.view;

import client.view.utils.ImagesUtils;
import controller.FakeController;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.*;

/**
 * Created by chiaravarini on 14/07/17.
 */
public class SelectCharacterPanel extends JPanel {

    private final Dimension CHARACTER_IMAGE_DIMENSION = calculatedImageCharDimension(10);//new Dimension(100,100);
    private final Dimension PLAYGROUND_IMAGE_DIMENSION = calculatedImageCharDimension(2.2);

    private final FakeController controller = new FakeController();

    private String characterChoosed = "";
    private String playgroundChoosed = "";

    public SelectCharacterPanel(){

        setLayout(new BorderLayout());

        JPanel buttonPanel = createBlackPanel();
        JButton doneButton = createButton("DONE");
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
        controller.getAllCharactersImagesJava().forEach((img, nameImage)-> {
            characterPanel.add(createImagePanel(img, nameImage, CHARACTER_IMAGE_DIMENSION));
        });
        JScrollPane characterScroll = new JScrollPane(characterPanel);

        center.add(createSectionTitle("Select one Character"));
        center.add(characterScroll);

        JPanel south = createBlackPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));

        JPanel playgroundPanel = createColorPanel();
        playgroundPanel.setLayout(new BoxLayout(playgroundPanel, BoxLayout.X_AXIS));

        controller.getAllPlaygroundsImages().forEach( (img,nameImage) -> {
            playgroundPanel.add(createImagePanel(img, nameImage, PLAYGROUND_IMAGE_DIMENSION));
        });
        JScrollPane playgroundScroll = new JScrollPane(playgroundPanel);

        south.add(createSectionTitle("Select one Playground"));
        south.add(playgroundScroll);

        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        exitButton.addActionListener(e->{
            MainFrame.getInstance().setContentPane(new HomePanel(controller.getUsername()));
        });

        doneButton.addActionListener(e->{
            // controller.startGame();
            MainFrame.getInstance().setContentPane(new LoadingPanel());
        });
    }

    private JPanel createImagePanel(final Image image, final String str, final Dimension dim){
        JPanel iconPanel = createColorPanel();
        int iconPadding = (int)dim.getWidth()/5;
        iconPanel.setBorder(BorderFactory.createEmptyBorder(iconPadding,iconPadding,iconPadding,iconPadding));
        JButton imageButton = new JButton();
        imageButton.setBorder(BorderFactory.createLineBorder(Color.black));
        imageButton.addActionListener(e->{

            System.out.println(((ImageIcon)imageButton.getIcon()).getDescription()) ;

        });
        ImageIcon icon = new ImageIcon(ImagesUtils.getScaledImage(image, (int)dim.getWidth(), (int)dim.getHeight()));
        icon.setDescription(str);
        imageButton.setIcon(icon);
        iconPanel.add(imageButton);
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
        button.setFont(new Font(getFont().getName(), Font.BOLD, 20));
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
}



       /* PlaygroundPanel playgroundView = controller.initializePlaygroundView( null );//model.getCharacterList());
        MainFrame.getInstance().setContentPane(playgroundView);

        UserInputController keyboardController = new UserInputController(playgroundView);
        playgroundView.addKeyListener(keyboardController);*/