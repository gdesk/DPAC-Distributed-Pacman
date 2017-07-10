package game;

import client.Playground;
import client.PlaygroundImpl$;
import client.gameElement.*;
import client.utils.Dimension;
import client.utils.PointImpl;
import controller.UserInputController;
import view.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Bottax on 04/07/2017.
 */
public class GameTest {

    private MainFrame view ;
    //private Game model;
    //private GameController controller;

    public GameTest(){

        Playground currentPlayground = createPlayground(); // TODO: Poi sarà nel model o nel controller, verrà passato da un metodo (arriva dal server)
        PlaygroundPanel playgroundView = initializePlaygroundView( currentPlayground , null /*model.getCharacterList()*/);
        view = MainFrame.getInstance();
        view.setPlayground(playgroundView);

        UserInputController keyboardController = new UserInputController(playgroundView);
        playgroundView.addKeyListener(keyboardController);
        System.out.println(playgroundView.hasFocus());


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

        view.renderCharacter( 45, 17,"pacman" , "left");

        // TODO: merge with character-model part in order to use this
        /*
        for(Character ch : characterList) {
            view.renderCharacter((int) ch.position().x(), (int) ch.position().y(), ch.name() /*"pacman"*/ /*, ch.direction()/*"up"*//*);
        } */

        return (PlaygroundPanel)view;
    }

    private Playground createPlayground() {
        Playground playground = PlaygroundImpl$.MODULE$.instance();
        playground.dimension_$eq(Dimension.apply(60, 30));

        List<Block> blocks = new ArrayList<>();
        for (int i = 1; i < 59; i++ ){
            //p.addBlock(new Block(new PointImpl(i, 1)));
            blocks.add(new Block(new PointImpl(i, 1)));
        }
        for (int i = 1; i < 59; i++ ){
            //p.addBlock(new Block(new PointImpl(i, 29)));
            blocks.add(new Block(new PointImpl(i, 29)));
        }
        for (int j = 1; j < 29; j++ ){
            //p.addBlock(new Block(new PointImpl(1, j)));
            blocks.add(new Block(new PointImpl(1, j)));
        }
        for (int j = 1; j < 29; j++ ){
            //p.addBlock(new Block(new PointImpl(59, j)));
            blocks.add(new Block(new PointImpl(59, j)));
        }
        for (int j = 5; j < 29; j = j + 5 ){
            for (int i = 3; i < 58 ; i++ ) {
                //p.addBlock(new Block(new PointImpl(i, j)));
                blocks.add(new Block(new PointImpl(i, j)));
            }
        }
        playground.blocks_$eq((scala.collection.immutable.List<Block>) blocks);

        List<client.gameElement.Eatable> eatables = new ArrayList<>();
        int pillId = 0;
        int dotId = 0;
        for (int j=7; j<29; j=j+5){
            if(j!=17) {
                for(int i=7; i<54; i++) {
                    if (i % 14 == 0) {
                        //p.addEatable(new VirtualPill(new PointImpl(i, j)));
                        eatables.add(new Pill("pill" + pillId++, new PointImpl(i, j)));
                    } else {
                        //p.addEatable(new VirtualDot(new PointImpl(i, j)));
                        eatables.add(new Dot("dot" + dotId++, new PointImpl(i, j)));
                    }
                }
            }
        }
        //p.addEatable(new VirtualFruit(new PointImpl(22, 17), Fruits.CHERRY));
        eatables.add(new Cherry("cherry" + 0, new PointImpl(22, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(23, 17), Fruits.STRAWBERRY));
        eatables.add(new Strawberry("strawberry" + 0, new PointImpl(23, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(24, 17), Fruits.ORANGE));
        eatables.add(new Orange("orange" + 0, new PointImpl(24, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(25, 17), Fruits.APPLE));
        eatables.add(new Apple("apple" + 0, new PointImpl(25, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(26, 17), Fruits.GRAPES));
        eatables.add(new Grapes("grapes" + 0, new PointImpl(26, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(27, 17), Fruits.GALAXIAN_SHIP));
        eatables.add(new GalaxianShip("galaxian ship" + 0, new PointImpl(27, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(28, 17), Fruits.BELL));
        eatables.add(new Bell("bell" + 0, new PointImpl(28, 17)));
        //p.addEatable(new VirtualFruit(new PointImpl(29, 17), Fruits.KEY));
        eatables.add(new Key("key" + 0, new PointImpl(29, 17)));
        playground.blocks_$eq((scala.collection.immutable.List<Block>) eatables);

        return playground;
    }

}

