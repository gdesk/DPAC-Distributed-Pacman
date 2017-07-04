package view;

import client.Playground;
import client.VirtualPlayground;
import client.gameElement.*;
import client.utils.Dimension;
import client.utils.Point;

import javax.swing.*;
import java.awt.*;

import static view.PlaygroundSettings.rows;
import static view.PlaygroundSettings.columns;
import static view.utils.FruitsImages.APPLE;

/**
 * Intial screen where it is possible start a new match.
 * User's Home of Distributed Pacman game.
 *
 * Created by chiaravarini on a 30/06/17.
 */

public class GameStartPanel extends JPanel {

    private PlaygroundPanel playground;


    public GameStartPanel(PlaygroundPanel playground){

        this.playground = playground;

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(1,47,74));

        JButton startGame = new JButton("<html><div align='center'>START<br>GAME</div></html>");
        startGame.setForeground(Color.WHITE);
        Font defaultFont = startGame.getFont();
        int fontSize = (int) MainFrame.DIMENSION.getHeight() /10;
        startGame.setFont(new Font(defaultFont.getName(), defaultFont.getStyle(), fontSize));
        startGame.setBorder(null);
        startGame.addActionListener(e->{
            MainFrame mainFrame = MainFrame.getInstance();
            /// TEST DI ESEMPIO ///////////////////////////
            /*Playground p = createPlayground();
            BulkPlaygroundPanel view = new BulkPlaygroundPanel();

            view.renderBlockList(Utils.getJavaList(p.getAllBlocks()));
            view.renderEatableList(Utils.getJavaList(p.getAllEatable()));
            view.renderCharacter(3,3,"pacman", "up");

            */
            mainFrame.setContentPane(this.playground);
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        this.add(startGame, BorderLayout.CENTER);
    }

    /*
    private VirtualPlayground createPlayground(){
        VirtualPlayground p = new VirtualPlayground(Dimension.apply(40,40));

        p.addBlock(new VirtualBlock(new Point (10, 2), 1,1));
        p.addBlock(new VirtualBlock(new Point (11, 2), 1,1));
        p.addBlock(new VirtualBlock(new Point (12, 2), 1,1));
        p.addBlock(new VirtualBlock(new Point (13, 2), 1,1));
        p.addBlock(new VirtualBlock(new Point (14, 2), 1,1));

        p.addBlock(new VirtualBlock(new Point (11, 5), 1,1));
        p.addBlock(new VirtualBlock(new Point (11, 6), 1,1));
        p.addBlock(new VirtualBlock(new Point (11, 7), 1,1));
        p.addBlock(new VirtualBlock(new Point (11, 8), 1,1));

        p.addEatable(new VirtualDot(new Point (20, 20)));
        p.addEatable(new VirtualDot(new Point (20, 21)));
        p.addEatable(new VirtualDot(new Point (20, 22)));
        p.addEatable(new VirtualPill(new Point (20, 23)));
        p.addEatable(new VirtualDot(new Point (20, 24)));
        p.addEatable(new VirtualDot(new Point (20, 25)));
        p.addEatable(new VirtualDot(new Point (20, 26)));

        p.addEatable(new VirtualFruit(new Point (25,24),Fruits.Cherry));
        p.addEatable(new VirtualFruit(new Point (25,25),Fruits.Strawberry));
        p.addEatable(new VirtualFruit(new Point (25,26),Fruits.Orange));
        p.addEatable(new VirtualFruit(new Point (25,27),Fruits.Apple));
        p.addEatable(new VirtualFruit(new Point (25,28),Fruits.Grapes));
        p.addEatable(new VirtualFruit(new Point (25,29),Fruits.GalaxianShip));
        p.addEatable(new VirtualFruit(new Point (25,30),Fruits.Bell));
        p.addEatable(new VirtualFruit(new Point (25,31),Fruits.Key));

        return p;
    } */
}
