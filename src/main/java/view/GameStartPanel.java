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


    public GameStartPanel(){

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
            Playground p = createPlayground();
            PlaygroundPanel view1 = new PlaygroundPanel(p.dimension().xDimension(), p.dimension().yDimension());
            PlaygroundPanel2 view = new PlaygroundPanel2();
            view.renderBlock(columns -1, rows -1);
            view.renderBlock(0,0);
            view.renderDot(10,10);
            view.renderPill(14,20);
            view.renderFruit(18,18, APPLE);
            view.renderCharacter(3,3,"pacman", "up");
            //view.renderBlock((int) Utils.getJavaList(p.getAllBlocks()).get(0).position().x(), (int) Utils.getJavaList(p.getAllBlocks()).get(0).position().y());
            //view.renderBlock((int) Utils.getJavaList(p.getAllBlocks()).get(1).position().x(), (int) Utils.getJavaList(p.getAllBlocks()).get(1).position().y());
            //view.renderBlock((int) Utils.getJavaList(p.getAllBlocks()).get(2).position().x(), (int) Utils.getJavaList(p.getAllBlocks()).get(2).position().y());
            //view.renderBlock((int) Utils.getJavaList(p.getAllBlocks()).get(3).position().x(), (int) Utils.getJavaList(p.getAllBlocks()).get(3).position().y());
           // view.renderBlock((int) Utils.getJavaList(p.getAllBlocks()).get(4).position().x(), (int) Utils.getJavaList(p.getAllBlocks()).get(4).position().y());

           // view.renderDot((int)Utils.getJavaList(p.getAllEatable()).get(0).position().x(), (int)Utils.getJavaList(p.getAllEatable()).get(0).position().y());
            mainFrame.setContentPane(view);
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        this.add(startGame, BorderLayout.CENTER);
    }

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
    }
}
