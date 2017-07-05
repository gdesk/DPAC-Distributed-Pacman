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
        BulkPlaygroundPanel view = new BulkPlaygroundPanel(playground.dimension());

        view.renderBlockList(Utils.getJavaList(playground.getAllBlocks()));
        view.renderEatableList(Utils.getJavaList(playground.getAllEatable()));

        view.renderCharacter( 45, 17,"pacman" , "left");

        // TODO: merge with character-model part in order to use this
        /*
        for(Character ch : characterList) {
            view.renderCharacter((int) ch.position().x(), (int) ch.position().y(), ch.name() /*"pacman"*/ /*, ch.direction()/*"up"*//*);
        } */

        return view;
    }



    private VirtualPlayground createPlayground() {
        VirtualPlayground p = new VirtualPlayground(Dimension.apply(60, 30));
        // TODO la dimensione dovrebbe essere decisa standard dal server e visualizzata diversamente a seconda dello schermo, ma non deve cambiare il numero di blocchi
        // tra diversi schermi con dimensioni diverse

        for (int i = 1; i < 59; i++ ){
            p.addBlock(new VirtualBlock(new Point(i, 1)));
        }

        for (int i = 1; i < 59; i++ ){
            p.addBlock(new VirtualBlock(new Point(i, 29)));
        }

        for (int j = 1; j < 29; j++ ){
            p.addBlock(new VirtualBlock(new Point(1, j)));
        }

        for (int j = 1; j < 29; j++ ){
            p.addBlock(new VirtualBlock(new Point(59, j)));
        }

        for (int j = 5; j < 29; j = j + 5 ){
            for (int i = 4; i < 57 ; i++ ) {
                p.addBlock(new VirtualBlock(new Point(i, j)));
            }
        }

        for (int j = 7; j < 29; j = j + 5 ){
            if (j != 17) {
                for (int i = 7; i < 54; i++) {
                    if (i % 14 == 0) {
                        p.addEatable(new VirtualPill(new Point(i, j)));
                    } else
                        p.addEatable(new VirtualDot(new Point(i, j)));
                }
            }
        }


        p.addEatable(new VirtualFruit(new Point(22, 17), Fruits.Cherry));
        p.addEatable(new VirtualFruit(new Point(23, 17), Fruits.Strawberry));
        p.addEatable(new VirtualFruit(new Point(24, 17), Fruits.Orange));
        p.addEatable(new VirtualFruit(new Point(25, 17), Fruits.Apple));
        p.addEatable(new VirtualFruit(new Point(26, 17), Fruits.Grapes));
        p.addEatable(new VirtualFruit(new Point(27, 17), Fruits.GalaxianShip));
        p.addEatable(new VirtualFruit(new Point(28, 17), Fruits.Bell));
        p.addEatable(new VirtualFruit(new Point(29, 17), Fruits.Key));

        return p;

    }

}

