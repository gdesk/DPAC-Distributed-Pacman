package client.view.match;

import client.controller.BaseControllerMatch;
import client.view.MainFrame;
import client.view.Res;
import client.view.Utils;
import client.view.base.SelectCharacterPanel;
import client.view.utils.Range;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
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

    private final PlayersPanel playerPanel = new PlayersPanel();
    private final SelectCharacterPanel nextView = new SelectCharacterPanel();

    private int numPLayer = 0;
    private int width = 1;

    public CreateTeamDialog(final JFrame frame){

        super(frame, "Create Team", true);
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 2));
            width = (int) frameDim.getWidth() / 2;
            setLocationRelativeTo(null);

            BaseControllerMatch.setTeamView(this);
            JPanel p = createBackgroundColorPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

            JPanel bottomPanel = createBackgroundColorPanel();
            bottomPanel.add(new JLabel("Looking for other players... "));

            JPanel numberPlayerPanel = createBackgroundColorPanel();
            numberPlayerPanel.setBorder(BorderFactory.createEmptyBorder(BOUNDS,BOUNDS,BOUNDS,BOUNDS));

            List<Range> ranges = Utils.scalaRangeToString(BaseControllerMatch.getRanges());
            JComboBox comboRange = new JComboBox();
            comboRange.addItem("");
            ranges.forEach(r -> comboRange.addItem(r.getMin() + "-" + (r.getMax() + 1)));
            comboRange.getComponent(1).setEnabled(false);

            DefaultListSelectionModel model = new DefaultListSelectionModel();
            model.addSelectionInterval(1,1);
            EnabledJComboBoxRenderer enableRenderer = new EnabledJComboBoxRenderer(model);
            comboRange.setRenderer(enableRenderer);

            comboRange.addActionListener(e-> {
                String rangeSelect = e.toString().substring(e.toString().length()-4, e.toString().length()-1);
                if(rangeSelect.equals("3-5")) {
                    Range rangeSelected = ranges.get(comboRange.getSelectedIndex()-1);
                    playerPanel.init(rangeSelected);
                    BaseControllerMatch.chosenRange(ranges.get(comboRange.getSelectedIndex()-1));
                }
            });

            numberPlayerPanel.add(comboRange);
            numberPlayerPanel.add(new JLabel("Select the number of players"));
            p.add(numberPlayerPanel);
            playerPanel.init(new Range(-1,-1));
            p.add(playerPanel);
            p.add(bottomPanel);
            add(p);
        }
    }

    @Override
    public void renderPlayerInMatch(final Integer playersNumber){
        for(int x = 0; x< playersNumber; x++) {
            playerPanel.markOK();
        }
        playerPanel.resetIndex();
        numPLayer = playersNumber;
    }

    @Override
    public void renderPlayerResponse(Boolean response) {
        if(response) {
            playerPanel.markOK();
            numPLayer ++;
        } else {
            playerPanel.markNO();
        }
    }

    @Override
    public void nextView(){
        dispose();
        nextView.setNumPlayer(numPLayer);
        MainFrame.getInstance().setContentPane(nextView);
    }

    /**
     * Circular panel to display the number of players in the team
     */
    private class PlayersPanel extends JPanel{

        private int index = 0;
        private int imgDim = 20;
        private List<JLabel> icons = new ArrayList<>();

        PlayersPanel(){
            setBackground(BACKGROUND_COLOR);
        }

        private synchronized void init(final Range rangePlayers){
            this.index = 0;
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

        private synchronized void markOK(){mark(Res.PLAYER_OK());}

        private synchronized void markNO(){mark(Res.PLAYER_NO());}

        private synchronized void mark(final String imageName){
            icons.get(index).setIcon(new ImageIcon(Utils.getScaledImage(Utils.getImage(imageName), imgDim, imgDim)));
            index = index+1;
            revalidate();
            repaint();
        }

        private void resetIndex(){
            this.index = 0;
        }
    }

    /**
     * JComboBox custom items
     */
    private class EnabledJComboBoxRenderer extends BasicComboBoxRenderer {

        static final long serialVersionUID = -984932432414L;

        private final ListSelectionModel enabledItems;
        private Color disabledColor = Color.lightGray;

        /**
         * Constructs a new renderer for a JComboBox which enables/disables items
         * based upon the parameter model.
         * @param enabled
         */
        public EnabledJComboBoxRenderer(ListSelectionModel enabled){
            super();
            this.enabledItems = enabled;
        }

        /**
         * Custom implementation to color items as enabled or disabled.
         */
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(!enabledItems.isSelectedIndex(index)) {
                if(isSelected) {
                    c.setBackground(UIManager.getColor("ComboBox.background"));
                } else {
                    c.setBackground(super.getBackground());
                }
                c.setForeground(disabledColor);
            } else {
                c.setBackground(super.getBackground());
                c.setForeground(super.getForeground());
            }
            return c;
        }
    }

}

