package client.view;

import client.model.MatchResult;
import controller.FakeController;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Home panel where user data is displayed and where you can start a new game
 * Created by chiaravarini on 11/07/17.
 */
public class HomePanel extends JPanel {

    private final static Color BACKGROUND_COLOR = Color.WHITE;
    private final List<MatchResult> matches;

    public HomePanel(final String username){

        this.matches = FakeController.getmatches(); //TODO controller.getMatches(username);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JButton startGame = createButton("START GAME");
        final JButton exit = createButton("EXIT");

        JPanel north = createColorPanel();
        north.setBorder(BorderFactory.createTitledBorder("Hi "+username));
        north.setLayout(new BorderLayout());

        JPanel buttonPanel = createColorPanel();
        buttonPanel.setLayout(new BorderLayout());

        JPanel northCenter = createColorPanel();
        northCenter.add(startGame);
        buttonPanel.add(northCenter, BorderLayout.CENTER);

        JPanel northEast = createColorPanel();
        northEast.add(exit);
        buttonPanel.add(northEast, BorderLayout.EAST);
        north.add(buttonPanel, BorderLayout.NORTH);

        JLabel label = new JLabel(new ImageIcon(Utils.getGif("whiteGhosts")));
        north.add(label, BorderLayout.CENTER);
        add(north);

        JTable table = createMatchTable(matches);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);

        startGame.addActionListener(e->{
            //  MainFrame.getInstance().setContentPane(new );

            CreateMatch createMatch = new CreateMatch(MainFrame.getInstance());
            createMatch.setVisible(true);

        });

        exit.addActionListener(e->{
            //controller.exit(username)
            MainFrame.getInstance().setContentPane(new LoginPanel());
        });
    }


    private int xTableIndex = 0;
    private JTable createMatchTable(final List<MatchResult> results){
        String[] columnNames = {"Result", "Date", "Score"};
        Object[][] data = new Object[results.size()][columnNames.length];
        results.forEach(res ->{
            data[xTableIndex][0] = res.result() ? "VICTORY" : "DEFEAT";
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            data[xTableIndex][1] = format1.format(res.date().getTime());
            data[xTableIndex][2] = "Score: "+res.score();
            xTableIndex = xTableIndex+1;
        });

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font(table.getFont().getName(), Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setGridColor(Color.BLACK);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.getTableHeader().setFont(new Font(table.getFont().getName(), Font.BOLD, 25));

        return table;
    }

    private JButton createButton(final String name){
        JButton button = new JButton(name);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setForeground(BACKGROUND_COLOR);
        button.setBorderPainted(false);
        button.setFont(new Font(getFont().getName(), Font.BOLD, 20));
        return button;
    }

    private JPanel createColorPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        return panel;
    }
}
