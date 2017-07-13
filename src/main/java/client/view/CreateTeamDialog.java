package client.view;

import client.model.Playground;
import client.model.controller.UserInputController;
import client.utils.IOUtils;
import client.view.playground.PlaygroundBuilderImpl;
import client.view.playground.PlaygroundPanel;
import client.view.playground.PlaygroundView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by chiaravarini on 12/07/17.
 */
public class CreateTeamDialog extends JDialog {


    public CreateTeamDialog(final JFrame frame){

        super(frame, "Create Team", true);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 2));
            setLocationRelativeTo(null);

            JButton starGame = new JButton("START");
            starGame.addActionListener(e->{
                PlaygroundPanel playgroundView = initializePlaygroundView( null );//model.getCharacterList());
                MainFrame.getInstance().setContentPane(playgroundView);

                UserInputController keyboardController = new UserInputController(playgroundView);
                playgroundView.addKeyListener(keyboardController);
                dispose();

            });

            JPanel numberPlayerPanel = new JPanel();
            String[] ranges = { "3-10", "10-15", "15-20" }; //TODO controller.getTeamRange()
            JComboBox comboRange = new JComboBox(ranges);
            numberPlayerPanel.add(comboRange);
            p.add(numberPlayerPanel);
            p.add(starGame);

            add(p);
        }
    }

    private PlaygroundPanel initializePlaygroundView ( List<Character> characterList) {
        IOUtils.saveLog("playground created !");
        Playground playground = IOUtils.getPlaygroundFromFile("default.dpac");


        PlaygroundView view = new PlaygroundBuilderImpl()
                .setColumns(playground.dimension().x())
                .setRows(playground.dimension().y())
                .setBackground(Color.black)
                .createPlayground();

        view.renderBlockList(Utils.getJavaList(playground.blocks()));
        view.renderEatableList(Utils.getJavaList(playground.eatables()));

        view.renderCharacter( 45, 17, new CharacterFactory().createPacman() , "left");

        IOUtils.saveLog("playground initialized !");

        return (PlaygroundPanel)view;
    }

}

