package game;

import client.Playground;
import client.VirtualPlayground;
import client.gameElement.*;
import client.utils.Dimension;
import client.utils.Point;
import view.*;

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

    }

    public static void main(String[] args) {
        GameTest game = new GameTest();
    }

    private PlaygroundPanel initializePlaygroundView (Playground playground, List<Character> characterList) {
        BulkPlaygroundPanel view = new BulkPlaygroundPanel();

        view.renderBlockList(Utils.getJavaList(playground.getAllBlocks()));
        view.renderEatableList(Utils.getJavaList(playground.getAllEatable()));

        // TODO: merge with character-model part in order to use this
        /*
        for(Character ch : characterList) {
            view.renderCharacter((int) ch.position().x(), (int) ch.position().y(), ch.name() /*"pacman"*/ /*, ch.direction()/*"up"*//*);
        } */

        return view;
    }



    private VirtualPlayground createPlayground() {
        VirtualPlayground p = new VirtualPlayground(Dimension.apply(40, 40));

        p.addBlock(new VirtualBlock(new Point(10, 2), 1, 1));
        p.addBlock(new VirtualBlock(new Point(11, 2), 1, 1));
        p.addBlock(new VirtualBlock(new Point(12, 2), 1, 1));
        p.addBlock(new VirtualBlock(new Point(13, 2), 1, 1));
        p.addBlock(new VirtualBlock(new Point(14, 2), 1, 1));

        p.addBlock(new VirtualBlock(new Point(11, 5), 1, 1));
        p.addBlock(new VirtualBlock(new Point(11, 6), 1, 1));
        p.addBlock(new VirtualBlock(new Point(11, 7), 1, 1));
        p.addBlock(new VirtualBlock(new Point(11, 8), 1, 1));

        p.addEatable(new VirtualDot(new Point(20, 20)));
        p.addEatable(new VirtualDot(new Point(20, 21)));
        p.addEatable(new VirtualDot(new Point(20, 22)));
        p.addEatable(new VirtualPill(new Point(20, 23)));
        p.addEatable(new VirtualDot(new Point(20, 24)));
        p.addEatable(new VirtualDot(new Point(20, 25)));
        p.addEatable(new VirtualDot(new Point(20, 26)));

        p.addEatable(new VirtualFruit(new Point(25, 24), Fruits.Cherry));
        p.addEatable(new VirtualFruit(new Point(25, 25), Fruits.Strawberry));
        p.addEatable(new VirtualFruit(new Point(25, 26), Fruits.Orange));
        p.addEatable(new VirtualFruit(new Point(25, 27), Fruits.Apple));
        p.addEatable(new VirtualFruit(new Point(25, 28), Fruits.Grapes));
        p.addEatable(new VirtualFruit(new Point(25, 29), Fruits.GalaxianShip));
        p.addEatable(new VirtualFruit(new Point(25, 30), Fruits.Bell));
        p.addEatable(new VirtualFruit(new Point(25, 31), Fruits.Key));

        return p;

    }

}

