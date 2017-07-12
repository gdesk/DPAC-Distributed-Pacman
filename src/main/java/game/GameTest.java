package game;

import client.model.Playground;
import client.model.controller.UserInputController;
import client.utils.IOUtils;
import client.view.*;

import java.awt.*;
import java.util.List;

/**
 * Created by Manuel Bottax on 04/07/2017.
 */
public class GameTest {

    private MainFrame view ;
    //private Game model;
    //private GameController client.model.controller;

    public GameTest(){

        Playground currentPlayground = createPlayground(); // TODO: Poi sarà nel model o nel client.model.controller, verrà passato da un metodo (arriva dal server)
        PlaygroundPanel playgroundView = initializePlaygroundView( currentPlayground , null /*model.getCharacterList()*/);
        view = MainFrame.getInstance();
        view.setContentPane(playgroundView);

        UserInputController keyboardController = new UserInputController(playgroundView);
        playgroundView.addKeyListener(keyboardController);

        System.out.println(playgroundView.hasFocus());

        IOUtils.saveLog("game can start !");

    }

    public static void main(String[] args) {
        GameTest game = new GameTest();
    }

    private PlaygroundPanel initializePlaygroundView (Playground playground, List<Character> characterList) {

        PlaygroundView view = new PlaygroundBuilderImpl()
                .setColumns(playground.dimension().x())
                .setRows(playground.dimension().y())
                .setBackground(Color.black)
                .createPlayground();

        view.renderBlockList(Utils.getJavaList(playground.blocks()));
        view.renderEatableList(Utils.getJavaList(playground.eatables()));

        view.renderCharacter( 45, 17, new CharacterFactory().createPacman() , "left");


        // TODO: merge with client.model.character.gameElement.character-model part in order to use this
        /*
        for(Character ch : characterList) {
            client.view.renderCharacter((int) ch.position().x(), (int) ch.position().y(), ch.name() /*"pacman"*/ /*, ch.direction()/*"up"*//*);
        } */

        IOUtils.saveLog("playground initialized !");

        return (PlaygroundPanel)view;
    }

    private Playground createPlayground() {

        IOUtils.saveLog("playground created !");
        return IOUtils.getPlaygroundFromFile("default.dpac");

    }

}

