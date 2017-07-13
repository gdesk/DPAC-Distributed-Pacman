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
public class CreateMatch extends JDialog {

    public CreateMatch(final JFrame frame){

        super(frame, "Registration", true);
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 3));
            setLocationRelativeTo(null);

            JButton starGame = new JButton("START");
            starGame.addActionListener(e->{
                PlaygroundPanel playgroundView = initializePlaygroundView( null );//model.getCharacterList());
                MainFrame.getInstance().setContentPane(playgroundView);

                UserInputController keyboardController = new UserInputController(playgroundView);
                playgroundView.addKeyListener(keyboardController);
                dispose();
            });

            add(starGame);
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

