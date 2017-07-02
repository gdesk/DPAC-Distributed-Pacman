package view;

import client.Playground;
import client.gameElement.Block;
import client.gameElement.Dot;
import client.gameElement.Pill;
import client.utils.*;
import client.utils.Dimension;
import client.utils.Point;

import javax.swing.*;
import java.awt.*;

/**
 * Intial screen where it is possible start a new match.
 * User's Home of Distributed Pacman game.
 *
 * Created by chiaravarini on a 30/06/17.
 */

public class GameStartPanel extends JPanel {

    private Playground selectedPlayground  = new Playground(new Dimension(50,50));

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
            Playground p = new Playground(Dimension.apply(50,50));

            p.addBlock(new Block(new Point (10, 2), 1,1));
            p.addBlock(new Block(new Point (11, 2), 1,1));
            p.addBlock(new Block(new Point (12, 2), 1,1));
            p.addBlock(new Block(new Point (13, 2), 1,1));
            p.addBlock(new Block(new Point (14, 2), 1,1));

            p.addBlock(new Block(new Point (11, 5), 1,1));
            p.addBlock(new Block(new Point (11, 6), 1,1));
            p.addBlock(new Block(new Point (11, 7), 1,1));
            p.addBlock(new Block(new Point (11, 8), 1,1));

            p.addEatable(new Dot(new Point (20, 20)));
            p.addEatable(new Dot(new Point (20, 21)));
            p.addEatable(new Dot(new Point (20, 22)));
            p.addEatable(new Pill(new Point (20, 23)));
            p.addEatable(new Dot(new Point (20, 24)));
            p.addEatable(new Dot(new Point (20, 25)));
            p.addEatable(new Dot(new Point (20, 26)));


            mainFrame.setContentPane(new PlaygroundPanel(p));
            mainFrame.repaint();
        });

        this.add(startGame, BorderLayout.CENTER);
    }
}
