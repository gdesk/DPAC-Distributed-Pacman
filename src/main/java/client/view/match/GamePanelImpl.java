package client.view.match;

import client.model.MatchImpl;
import client.model.PlaygroundImpl;
import client.model.utils.Point;
import client.view.MainFrame;
import client.view.PacmanView;
import client.view.Utils;
import client.view.playground.MicroMapPanel;
import client.view.playground.PlaygroundPanel;
import client.view.playground.PlaygroundView;
import client.view.utils.JComponentsUtils;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

import static client.view.utils.JComponentsUtils.BACKGROUND_COLOR;
import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * A class wich implements the GamePanel interface
 * Created by Chiara Varini on 10/07/17.
 */
public class GamePanelImpl extends JLayeredPane implements GamePanel{

    private static final int BOUNDS = 200;
    private static final int LIVES_IMAGE_DIM = 40;

    private PlaygroundView playground;

    private final MicroMapPanel microMap = new MicroMapPanel();
    private final JLabel score = new JLabel("Score: 0");
    private JPanel livesPanel = new JPanel();

    public GamePanelImpl(final PlaygroundPanel playground) {
        this.playground = playground;

        playground.setBounds(0,0, (int)MainFrame.DIMENSION.getWidth(), (int)MainFrame.DIMENSION.getHeight());

        add(playground, 1);
        add(microMap, 0);
        addScorePanel();
        addLivesPanel(3);

    }

    @Override
    public void showResult(final String result) {
        VictoryDialog victoryDialog = new VictoryDialog(MainFrame.getInstance(), result);
        victoryDialog.setVisible(true);
    }

    @Override
    public void move(final Image characterImage, final Color characterColor, final Point<Integer,Integer> oldPosition, final Point<Integer,Integer> newPosition) {
        if (playground != null) {
            playground.removeCharacter(oldPosition.x(), oldPosition.y());
            playground.renderCharacter(newPosition.x(), newPosition.y(), characterImage);
            microMap.moveCharacter(characterColor, newPosition, oldPosition);
        }
        playground.renderEatableList(Utils.getJavaList(PlaygroundImpl
                .eatables())
                .stream()
                .filter(e->e.position().x()==oldPosition.x() && e.position().y()==oldPosition.y())
                .collect(Collectors.toList()));

        revalidate();
        repaint();
    }

    @Override
    public void renderScore(final int score){
        this.score.setText("Score: "+score);
        revalidate();
        repaint();
    }

    @Override
    public void updateLives(final int livesNumber){
        remove(livesPanel);
        addLivesPanel(livesNumber);
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

    private void addScorePanel(){
        score.setForeground(BACKGROUND_COLOR);
        score.setFont(new Font(score.getFont().getName(), Font.BOLD, FONT_SIZE));
        score.setBounds(0,0, BOUNDS,BOUNDS/2);
        add(score, 0);
    }

    private void addLivesPanel(final int livesNumber) {
        if (MatchImpl.myCharacter().name().equals("pacman")) {
            livesPanel = JComponentsUtils.createTrasparentPanel();
            livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.Y_AXIS));


            for (int i = 0; i < livesNumber; i++) {
                JLabel pacLive = new JLabel();
                ImageIcon icon = new ImageIcon(Utils.getScaledImage(new PacmanView().getCharacterRight(), LIVES_IMAGE_DIM, LIVES_IMAGE_DIM));
                pacLive.setIcon(icon);
                livesPanel.add(pacLive);
            }

            livesPanel.setBounds(0, BOUNDS / 2, BOUNDS, (int) MainFrame.DIMENSION.getWidth());
            add(livesPanel, 0);
        }
    }
}
