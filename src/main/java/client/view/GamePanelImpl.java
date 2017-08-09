package client.view;

import client.controller.BaseControllerMatch;
import client.controller.BaseControllerMatch$;
import client.controller.ControllerMatch;
import client.model.PlaygroundImpl;
import client.model.utils.Point;
import client.view.playground.MicroMapPanel;
import client.view.playground.PlaygroundView;
import client.view.utils.ImagesUtils;
import client.view.utils.JComponentsUtils;
//import controller.FakeController;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.BACKGROUND_COLOR;
import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class GamePanelImpl extends JLayeredPane implements GamePanel{

    private PlaygroundView playground;

    private final JLabel score = new JLabel("Score: 0");
    private int livesNum = 3;
    private final MicroMapPanel microMap = new MicroMapPanel(PlaygroundImpl.instance());

    public GamePanelImpl(final Container playground) {

        this.playground = (PlaygroundView) playground;
        this.add(playground, 0);
        add(microMap, 1);
        addScorePanel();
        addLivesPanel(3);
    }


    public void showResult(final String result) {

        JPanel victoryPanel = JComponentsUtils.createTrasparentPanel();
        victoryPanel.setLayout(new GridBagLayout());
        victoryPanel.setBounds(0, 0, (int) MainFrame.DIMENSION.getWidth(), (int) MainFrame.DIMENSION.getHeight());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel l = new JLabel(result);
        l.setForeground(BACKGROUND_COLOR);
        l.setFont(new Font(l.getFont().getName(), Font.BOLD, FONT_SIZE * 3));

        victoryPanel.add(l, gbc);
        add(victoryPanel, 1);
    }

    @Override
    public void move(final Image characterImage, final Point<Integer,Integer> oldPosition, final Point<Integer,Integer> newPosition) {

        if (playground != null) {
            playground.removeCharacter(oldPosition.x(), oldPosition.y());
            playground.renderCharacter(newPosition.x(), newPosition.y(), characterImage);
            microMap.moveCharacter(Color.red, newPosition, oldPosition);
        }
        revalidate();
        repaint();
    }

    private void addScorePanel(){
        score.setForeground(BACKGROUND_COLOR);
        score.setFont(new Font(score.getFont().getName(), Font.BOLD, FONT_SIZE ));
        score.setBounds(0,0, 100,50);
        add(score, 1);
    }

    private void addLivesPanel(final int livesNumber){

        JPanel livesPanel = JComponentsUtils.createTrasparentPanel();
        livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.Y_AXIS));

        for (int i=0; i<livesNumber;i++){
            JLabel pacLive = new JLabel();
            ImageIcon icon = new ImageIcon(ImagesUtils.getScaledImage(new PacmanView().getCharacterRight(),40,40));
            pacLive.setIcon(icon);
            livesPanel.add(pacLive);
        }

        livesPanel.setBounds(0,50,100, (int)MainFrame.DIMENSION.getWidth());

        add(livesPanel, 1);
    }

    @Override
    public void updateScore(final int score){
        this.score.setText("Score: "+score);
        revalidate();
        repaint();
    }

    @Override
    public void updateLives(final int livesNumber){
        this.livesNum = livesNumber;
    }

    @Override
    public void gameOver(){
        GameOverDialog gameoverDialog = new GameOverDialog(MainFrame.getInstance());
        gameoverDialog.setVisible(true);
    }

    @Override
    public void deleteCharacter(Point<Integer, Integer> characterToDeletePosition) {
        playground.removeCharacter(characterToDeletePosition.x(), characterToDeletePosition.y());
    }


}
