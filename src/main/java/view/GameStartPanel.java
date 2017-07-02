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

            p.addBlock(new Block(new Point (10.0, 2.0), 1,1));
            p.addBlock(new Block(new Point (11.0, 2.0), 1,1));
            p.addBlock(new Block(new Point (12.0, 2.0), 1,1));
            p.addBlock(new Block(new Point (13.0, 2.0), 1,1));
            p.addBlock(new Block(new Point (14.0, 2.0), 1,1));

            p.addBlock(new Block(new Point (11.0, 5.0), 1,1));
            p.addBlock(new Block(new Point (11.0, 6.0), 1,1));
            p.addBlock(new Block(new Point (11.0, 7.0), 1,1));
            p.addBlock(new Block(new Point (11.0, 8.0), 1,1));

            p.addEatable(new Dot(new Point (20.0, 20.0)));
            p.addEatable(new Dot(new Point (20.0, 21.0)));
            p.addEatable(new Dot(new Point (20.0, 22.0)));
            p.addEatable(new Pill(new Point (20.0, 23.0)));
            p.addEatable(new Dot(new Point (20.0, 24.0)));
            p.addEatable(new Dot(new Point (20.0, 25.0)));
            p.addEatable(new Dot(new Point (20.0, 26.0)));


            mainFrame.setContentPane(new PlaygroundPanel(p));
            mainFrame.repaint();
        });

        this.add(startGame, BorderLayout.CENTER);
    }
}
