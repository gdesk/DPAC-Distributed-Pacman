package game;

import client.model.Playground;
import client.model.PlaygroundImpl$;
import client.model.controller.UserInputController;
import client.model.gameElement.*;
import client.model.utils.Dimension;
import client.model.utils.PointImpl;
import client.model.controller.UserInputController;
import client.utils.IOUtils;
import client.view.*;
import scala.collection.mutable.ListBuffer;

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
        //view.setPlayground(playgroundView);

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
        /*
        Playground playground = PlaygroundImpl$.MODULE$.instance();
        playground.dimension_$eq(Dimension.apply(60, 30));

        ListBuffer<Block> blocks = new ListBuffer<>();

        for (int i = 1; i < 59; i++ ){
            //p.addBlock(new Block(new PointImpl(i, 1)));
            blocks.$plus$eq(new Block(new PointImpl(i, 1)));
        }
        for (int i = 1; i < 59; i++ ){
            //p.addBlock(new Block(new PointImpl(i, 29)));
            blocks.$plus$eq(new Block(new PointImpl(i, 29)));
        }
        for (int j = 1; j < 29; j++ ){
            //p.addBlock(new Block(new PointImpl(1, j)));
            blocks.$plus$eq(new Block(new PointImpl(1, j)));
        }
        for (int j = 1; j < 29; j++ ){
            //p.addBlock(new Block(new PointImpl(59, j)));
            blocks.$plus$eq(new Block(new PointImpl(59, j)));
        }
        for (int j = 5; j < 29; j = j + 5 ){
            for (int i = 3; i < 58 ; i++ ) {
                //p.addBlock(new Block(new PointImpl(i, j)));
                blocks.$plus$eq(new Block(new PointImpl(i, j)));
            }
        }
        playground.blocks_$eq(blocks.toList());

        ListBuffer<Eatable> eatables = new ListBuffer<>();
        int pillId = 0;
        int dotId = 0;
        for (int j=7; j<29; j=j+5){
            if(j!=17) {
                for(int i=7; i<54; i++) {
                    if (i % 14 == 0) {
                        //p.addEatable(new VirtualPill(new PointImpl(i, j)));
                        eatables.$plus$eq(new Pill("pill" + pillId++, new PointImpl(i, j)));
                    } else {
                        //p.addEatable(new VirtualDot(new PointImpl(i, j)));
                        eatables.$plus$eq(new Dot("dot" + dotId++, new PointImpl(i, j)));
                    }
                }
            }
        }
        //p.addEatable(new VirtualFruit(new PointImpl(22, 17), Fruits.CHERRY));
        eatables.$plus$eq(new Cherry("cherry" + 0, new PointImpl(22, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(23, 17), Fruits.STRAWBERRY));
        eatables.$plus$eq(new Strawberry("strawberry" + 0, new PointImpl(23, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(24, 17), Fruits.ORANGE));
        eatables.$plus$eq(new Orange("orange" + 0, new PointImpl(24, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(25, 17), Fruits.APPLE));
        eatables.$plus$eq(new Apple("apple" + 0, new PointImpl(25, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(26, 17), Fruits.GRAPES));
        eatables.$plus$eq(new Grapes("grapes" + 0, new PointImpl(26, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(27, 17), Fruits.GALAXIAN_SHIP));
        eatables.$plus$eq(new GalaxianShip("galaxian ship" + 0, new PointImpl(27, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(28, 17), Fruits.BELL));
        eatables.$plus$eq(new Bell("bell" + 0, new PointImpl(28, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(29, 17), Fruits.KEY));
        eatables.$plus$eq(new Key("key" + 0, new PointImpl(29, 17)));
        playground.eatables_$eq(eatables.toList());

        return playground;
        */

    }

}

