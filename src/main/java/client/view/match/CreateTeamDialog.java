package client.view.match;

import client.controller.BaseControllerMatch;
import client.controller.ControllerMatch;
import client.view.Res;
import client.view.Utils;
import client.view.base.SelectCharacterPanel;
import client.view.utils.Range;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static client.view.utils.JComponentsUtils.BACKGROUND_COLOR;
import static client.view.utils.JComponentsUtils.createBackgroundColorPanel;

/**
 * Panel used to create a new play team
 * Created by Chiara Varini on 12/07/17.
 */
public class CreateTeamDialog extends JDialog implements CreateTeamView{

    private static final int BOUNDS = 10;

    private final SelectCharacterPanel nextView = new SelectCharacterPanel();
    private final ControllerMatch controller = BaseControllerMatch.instance();
    private final PlayersPanel playerPanel = new PlayersPanel();
    private final JButton starGame = new JButton("START");

    private int width = 1;
    private  int numPlayer  = 1;

    public CreateTeamDialog(final JFrame frame){

        super(frame, "Create Team", true);

        controller.setTeamView(this);

        JPanel p = createBackgroundColorPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 2));
            width = (int) frameDim.getWidth() / 2;
            setLocationRelativeTo(null);

            JPanel buttonPanel = createBackgroundColorPanel();
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(BOUNDS,BOUNDS,BOUNDS,BOUNDS));
            starGame.setEnabled(false);
            starGame.addActionListener(e->{
                dispose();
                frame.setContentPane(nextView);
            });

            JButton addFiends = new JButton("+ ADD FRIENDS");
            addFiends.addActionListener(e->{
                AddFriendsDialog friendsDialog =  new AddFriendsDialog(this);
                friendsDialog.setVisible(true);
            });
            buttonPanel.add(starGame);
            buttonPanel.add(addFiends);

            JPanel numberPlayerPanel = createBackgroundColorPanel();
            numberPlayerPanel.setBorder(BorderFactory.createEmptyBorder(BOUNDS,BOUNDS,BOUNDS,BOUNDS));

            List<Range> ranges = Utils.scalaRangeToString(controller.getRanges());
            JComboBox comboRange = new JComboBox();
            comboRange.addItem("");
            ranges.forEach(r -> comboRange.addItem(r.getMin() + "-"+ (r.getMax()+1)));
            comboRange.addActionListener(e->{
                Range rangeSelected = ranges.get(comboRange.getSelectedIndex()-1);
                playerPanel.init(rangeSelected);
                controller.rangeChosen(ranges.get(comboRange.getSelectedIndex()-1));
            });

            numberPlayerPanel.add(comboRange);
            numberPlayerPanel.add(new JLabel("Select the number of players"));
            p.add(numberPlayerPanel);
            playerPanel.init(new Range(-1,-1));
            p.add(playerPanel);
            p.add(buttonPanel);
            add(p);
        }
    }

    @Override
    public void renderPlayerInMatch(final Integer response){
        for(int x=0; x<response; x++) {
            playerPanel.markOK();
        }
        playerPanel.resetIndex();
    }

    @Override
    public void renderPlayerResponse(Boolean response) {
        if(response) {
            playerPanel.markOK();
        } else {
            playerPanel.markNO();
        }
    }

    private class PlayersPanel extends JPanel{

        private int index = 0;
        private int imgDim = 20;
        private Range rangePlayers;
        private int numPlayerOK = 1;
        private List<JLabel> icons = new ArrayList<>();

        PlayersPanel(){
            setBackground(BACKGROUND_COLOR);
        }

        private synchronized void init(final Range rangePlayers){
            this.index = 0;
            this.rangePlayers = rangePlayers;
            icons.clear();
            removeAll();
            for(int i = 0; i<rangePlayers.getMax()+1; i++) {
                JLabel l = new JLabel();
                imgDim = width / (rangePlayers.getMax()+2);
                l.setIcon(new ImageIcon(Utils.getScaledImage(Utils.getImage(Res.PLAYER_BUTTON()), imgDim, imgDim)));
                icons.add(l);
                add(l);
            }
            revalidate();
            repaint();
        }

        private synchronized void markOK(){
            icons.get(index).setIcon(new ImageIcon(Utils.getScaledImage(Utils.getImage(Res.PLAYER_OK()), imgDim, imgDim)));
            index = index+1;
            if(index>=rangePlayers.getMin()){
                starGame.setEnabled(true);
            }
            revalidate();
            repaint();
        }

        private synchronized void markNO(){
            icons.get(index).setIcon(new ImageIcon(Utils.getScaledImage(Utils.getImage(Res.PLAYER_NO()), imgDim, imgDim)));
            index = index+1;
            revalidate();
            repaint();
        }

        private void resetIndex(){
            this.index = 0;
        }
    }
}

