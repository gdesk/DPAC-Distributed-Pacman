package client.view.playground;

import client.model.Direction;
import client.view.MainFrame;
import client.view.PacmanView;
import client.view.utils.ImagesUtils;
import client.view.utils.JComponentsUtils;

import javax.swing.*;
import java.awt.*;

import static client.view.utils.JComponentsUtils.BACKGROUND_COLOR;
import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class GamePanel extends JLayeredPane {

    private PlaygroundView playground;

    private int currentX = 20;
    private int currentY = 20; //TODO CAMBIA

    private final JLabel score = new JLabel("Score: 0");

    public GamePanel(final Container playground) {

        this.playground = (PlaygroundView) playground;
        this.add(playground, 0);
        addMicroMap();
        addScorePanel();
        addLivesPanel(3);
    }

    public void addMicroMap(){
        JPanel microMap = new MicroMapPanel();
        add(microMap, 1);
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

    public void move(final Direction dir) {
        if (playground != null) {
            playground.removeCharacter(currentX, currentY);
            updatePosition(dir);
            playground.renderCharacter(currentX, currentY, new PacmanView(), dir);
        }
        addMicroMap();
        addScorePanel();
        addLivesPanel(currentX%10);
    }

    private void updatePosition(final Direction dir){
        switch (dir){
            case UP: currentY = currentY-1; break;
            case DOWN: currentY = currentY+1; break;
            case LEFT: currentX = currentX-1; break;
            case RIGHT: currentX = currentX+1; break;
        }
    }

    private void addScorePanel(){
        score.setForeground(BACKGROUND_COLOR);
        score.setFont(new Font(score.getFont().getName(), Font.BOLD, FONT_SIZE ));
        score.setBounds(0,0, 500,50);
        add(score, 1);
    }

    public void addLivesPanel(final int livesNumber){

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


    public void aupdateScore(final int score){
        this.score.setText("Score: "+score);
        revalidate();
        repaint();
    }

}
