package view;

import javax.swing.*;
import java.awt.*;

/**
 * Intial screen where it is possible start a new match.
 * User's Home of Distributed Pacman game.
 *
 * Created by chiaravarini on a 30/06/17.
 */

public class InitialScreen extends JPanel {

    public InitialScreen(){

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(1,47,74));

        JButton startGame = new JButton("<html><div align='center'>START<br>GAME</div></html>");
        startGame.setForeground(Color.WHITE);
        Font defaultFont = startGame.getFont();
        int fontSize = (int)MainFrame.DIMENSION.getHeight() /10;
        startGame.setFont(new Font(defaultFont.getName(), defaultFont.getStyle(), fontSize));
        startGame.setBorder(null);

        this.add(startGame, BorderLayout.CENTER);
    }
}
