package client.view;

import client.view.utils.ImagesUtils;
import controller.FakeController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel used to create a new play team
 * Created by chiaravarini on 12/07/17.
 */
public class CreateTeamDialog extends JDialog {

    private final FakeController controller = new FakeController();
    private final PlayersPanel playerPanel = new PlayersPanel();
    private int width = 1;

    private  int numPlayer  = 1;
    public CreateTeamDialog(final JFrame frame){

        super(frame, "Create Team", true);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        if (frame != null) {

            Dimension frameDim = frame.getSize();
            setMinimumSize(new Dimension((int) frameDim.getWidth() / 2, (int) frameDim.getHeight() / 2));
            width = (int) frameDim.getWidth() / 2;
            setLocationRelativeTo(null);

            JPanel buttonPanel = new JPanel();
            JButton starGame = new JButton("START");
            starGame.addActionListener(e->{
                dispose();
                frame.setContentPane(new SelectCharacterPanel());
            });

            JButton addFiends = new JButton("+ ADD FRIENDS");
            addFiends.addActionListener(e->{
                AddFriendsDialog friendsDialog =  new AddFriendsDialog(this);
                friendsDialog.setVisible(true);
            });
            buttonPanel.add(starGame);
            buttonPanel.add(addFiends);

            JPanel numberPlayerPanel = new JPanel();
            List<String> ranges = controller.getTeamRange();
            JComboBox comboRange = new JComboBox(ranges.toArray());

            comboRange.addActionListener(e->{
                switch((String)comboRange.getSelectedItem()){
                    case "3-5": numPlayer = 5; break;
                    case "5-10": numPlayer = 10; break;
                    case "10-15": numPlayer = 15; break;
                    case "15-20": numPlayer = 20; break;
                }

                playerPanel.init(numPlayer);
            });

            numberPlayerPanel.add(comboRange);
            numberPlayerPanel.add(new JLabel("Select the number of players"));
            p.add(numberPlayerPanel);
            playerPanel.init(5);
            p.add(playerPanel);
            p.add(buttonPanel);
            add(p);
        }
    }

    synchronized public void update(Boolean response){   //TODO passare il nome del player?
        if(response){
            playerPanel.markOK();
        } else {
            playerPanel.markNO();
        }
    }

    private class PlayersPanel extends JPanel{

        private int index = 0;
        private int imgDim = 20;
        private int numPlayer = 1;
        private List<JLabel> icons = new ArrayList<>();

        private synchronized void init(final int numPlayers ){
            this.index = 0;
            this.numPlayer = numPlayers;
            icons.clear();
            removeAll();
            for(int i = 0; i<numPlayers; i++) {
                JLabel l = new JLabel();
                imgDim = width / (numPlayers+1);
                l.setIcon(new ImageIcon(ImagesUtils.getScaledImage(Utils.getImage(Res.PLAYER_BUTTON()), imgDim, imgDim)));
                icons.add(l);
                add(l);
            }
            revalidate();
            repaint();
        }

        private synchronized void markOK(){
            System.out.println(index);
            icons.get(index).setIcon(new ImageIcon(ImagesUtils.getScaledImage(Utils.getImage(Res.PLAYER_OK()), imgDim, imgDim)));
            index = index+1%numPlayer;
            revalidate();
            repaint();
        }

        private synchronized void markNO(){
            icons.get(index).setIcon(new ImageIcon(ImagesUtils.getScaledImage(Utils.getImage(Res.PLAYER_NO()), imgDim, imgDim)));
            index = index+1%numPlayer;
            revalidate();
            repaint();
        }
    }

}

