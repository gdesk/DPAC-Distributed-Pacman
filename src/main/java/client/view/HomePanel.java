package client.view;

import client.controller.BaseControllerUser;
import client.controller.ControllerUser;
import client.model.MatchResult;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static client.view.utils.JComponentsUtils.*;

/**
 * Home panel where user data is displayed and where you can start a new game
 * Created by chiaravarini on 11/07/17.
 */
public class HomePanel extends JPanel {

    private final List<MatchResult> matches;
    private final ControllerUser controller = BaseControllerUser.instance();
    private final JButton startGame =  createBlackButton("START GAME");

    public HomePanel(final String username){

        this.matches = Utils.getJavaList(controller.getAllMatchesResults(username));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JButton exit = createBlackButton("EXIT");

        JPanel north = createBackgroundColorPanel();
        north.setBorder(BorderFactory.createTitledBorder("Hi "+username));
        north.setLayout(new BorderLayout());

        JPanel buttonPanel = createBackgroundColorPanel();
        buttonPanel.setLayout(new BorderLayout());

        JPanel northCenter = createBackgroundColorPanel();
        northCenter.add(startGame);
        buttonPanel.add(northCenter, BorderLayout.CENTER);

        JPanel northEast = createBackgroundColorPanel();
        northEast.add(exit);
        buttonPanel.add(northEast, BorderLayout.EAST);
        north.add(buttonPanel, BorderLayout.NORTH);

        JLabel label = new JLabel(new ImageIcon(Utils.getGif(Res.HOME_IMAGE())));
        north.add(label, BorderLayout.CENTER);
        add(north);

        JTable table = createMatchTable(matches);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane);

        startGame.addActionListener(e->{
            CreateTeamDialog createMatch = new CreateTeamDialog(MainFrame.getInstance());
            createMatch.setVisible(true);
        });

        exit.addActionListener(e->{
            controller.logout();
            MainFrame.getInstance().setContentPane(new LoginPanel());
        });
    }

    public void showRequest(){
        RequestDialog requestDialog = new RequestDialog(MainFrame.getInstance());
        requestDialog.setVisible(true);
    }

    private int xTableIndex = 0;
    private JTable createMatchTable(final List<MatchResult> results) {
        String[] columnNames = {"Result", "Date", "Score"};
        Object[][] data = new Object[results.size()][columnNames.length];
        results.forEach(res -> {
            data[xTableIndex][0] = res.result() ? "VICTORY" : "DEFEAT";
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            data[xTableIndex][1] = format1.format(res.date().getTime());
            data[xTableIndex][2] = "Score: " + res.score();
            xTableIndex = xTableIndex + 1;
        });

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font(table.getFont().getName(), Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setGridColor(LOGIN_COLOR);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.getTableHeader().setFont(new Font(table.getFont().getName(), Font.BOLD, 25));

        return table;
    }


}
