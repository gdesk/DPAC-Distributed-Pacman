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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel north = new JPanel();
        north.setBackground(BACKGROUND_COLOR);
        north.add(startGame);
        add(north);

        JPanel center = new JPanel();
        center.setBackground(BACKGROUND_COLOR);
        JLabel label = new JLabel(new ImageIcon(Utils.getGif("whiteGhosts")));
        center.add(label);
        add(center);

        JTable table = createMatchTable(results);
        table.setFont(new Font(table.getFont().getName(), Font.PLAIN, 15));
        table.setRowHeight(25);
        table.setShowHorizontalLines(true);
        table.getTableHeader().setFont(new Font(table.getFont().getName(), Font.BOLD, 25));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);

        startGame.addActionListener(e->{
            //startGame
            // MainFrame.getInstance().setContentPane(new PlaygroundPanel());
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

        return new JTable(data, columnNames);
    }
}
