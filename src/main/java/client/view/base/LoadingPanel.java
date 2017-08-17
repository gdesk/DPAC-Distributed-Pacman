package client.view.base;

import client.controller.BaseControllerCharacter;
import client.model.Direction;
import client.model.MatchImpl;
import client.model.PlayerImpl;
import client.model.PlaygroundImpl;
import client.model.character.Character;
import client.view.*;
import client.view.match.GamePanelImpl;
import client.view.playground.PlaygroundBuilderImpl;
import client.view.playground.PlaygroundPanel;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.*;

/**
 * Panel displayed while loading all the game data
 * and renders the playground once you've uploaded all the info
 * Created by Chiara Varini on 14/07/17.
 */
public class LoadingPanel extends JPanel implements LoadingView {

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

    public void renderGamePanel(){

        PlaygroundPanel view = new PlaygroundBuilderImpl()
                .setColumns(PlaygroundImpl.dimension().x())
                .setRows(PlaygroundImpl.dimension().y())
                .setBackground(Color.black)
                .createPlayground();

        view.renderBlockList(Utils.getJavaList(PlaygroundImpl.blocks()));
        view.renderEatableList(Utils.getJavaList(PlaygroundImpl.eatables()));

        Character myChar = MatchImpl.myCharacter();
        Image myView = new CharacterFactory().createPacman().getCharacterRight();//Utils.getJavaMap(BaseControllerCharacter.getCharacterImages(PlayerImpl.ip())).get(Direction.RIGHT);
        view.renderCharacter((int)myChar.position().x(), (int)myChar.position().y(), myView);

        GamePanelImpl gp = new GamePanelImpl(view);

        UserInputController keyboardController = new UserInputController();
        view.addKeyListener(keyboardController);

        MainFrame.getInstance().setContentPane(gp);
        BaseControllerCharacter.setView(gp);
    }
}
