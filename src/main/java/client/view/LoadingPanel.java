package client.view;

import client.controller.BaseControllerCharacter;
import client.model.Playground;
import client.model.PlaygroundImpl;
import client.view.playground.PlaygroundBuilderImpl;
import client.view.playground.PlaygroundPanel;
import client.view.playground.PlaygroundView;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.*;

/**
 * Panel displayed while loading all the game data
 * Created by chiaravarini on 14/07/17.
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

        Playground playground = PlaygroundImpl.instance();

        PlaygroundView view = new PlaygroundBuilderImpl()
                .setColumns(playground.dimension().x())
                .setRows(playground.dimension().y())
                .setBackground(Color.black)
                .createPlayground();

        view.renderBlockList(Utils.getJavaList(playground.blocks()));
        view.renderEatableList(Utils.getJavaList(playground.eatables()));

        GamePanelImpl gp = new GamePanelImpl((PlaygroundPanel)view);

        UserInputController keyboardController = new UserInputController(BaseControllerCharacter.instance());
        ((PlaygroundPanel)view).addKeyListener(keyboardController);

        MainFrame.getInstance().setContentPane(gp);
        BaseControllerCharacter.instance().setView(gp);
    }
}
