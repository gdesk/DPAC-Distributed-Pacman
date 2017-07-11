package client.view;

import client.model.MatchResult;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by chiaravarini on 11/07/17.
 */
public class HomePanel extends JPanel {

    private final static Color BACKGROUND_COLOR = Color.WHITE;

    private final Dimension MainFrameDimension = MainFrame.DIMENSION;
    private final JButton startGame = new JButton("START GAME");

    public HomePanel(final List<MatchResult> results){
        setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(BACKGROUND_COLOR);

        startGame.setMinimumSize(new Dimension((int)MainFrameDimension.getWidth()/10, (int)MainFrameDimension.getHeight()/3));
        north.add(startGame);
        add(north, BorderLayout.NORTH);

        JPanel ceneter = new JPanel();

        JPanel matchesPanel = new JPanel();
        matchesPanel.setLayout(new BoxLayout(matchesPanel, BoxLayout.Y_AXIS));
        addResults(matchesPanel, results);
        JScrollPane scroll = new JScrollPane(matchesPanel);
        add(scroll, BorderLayout.SOUTH);

    }

    private void addResults(final JPanel matchesPanel, final List<MatchResult> results){

        results.forEach( res ->{
            JPanel p = new JPanel(new BorderLayout());
            p.setMaximumSize(new Dimension(MainFrameDimension.width, (int)MainFrameDimension.getHeight()/30));

            JPanel resultPanel = new JPanel();
            resultPanel.add(new JLabel( res.result() ? "VICTORY" : "DEFEAT"));
            p.add(resultPanel, BorderLayout.WEST);

            JPanel datePanel = new JPanel();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            datePanel.add(new JLabel(format1.format(res.date().getTime())));
            p.add(datePanel, BorderLayout.CENTER);

            JPanel scorePanel = new JPanel();
            scorePanel.add(new JLabel("Score: "+res.score()));
            p.add(scorePanel, BorderLayout.EAST);

            matchesPanel.add(p);
        });
    }
}
