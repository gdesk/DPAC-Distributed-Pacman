package client.view.playground;

import client.model.Direction;
import client.view.MainFrame;
import client.view.PacmanView;

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


    public GamePanel(final Container playground) {

        this.playground = (PlaygroundView) playground;
        this.add(playground, 0);  //TODO CAMBIAAA!!!!
        addMicroMap();
    }

    public void addMicroMap(){
        JPanel microMap = new JPanel();
        microMap.setBounds((int) MainFrame.DIMENSION.getWidth()-200, (int) MainFrame.DIMENSION.getHeight()-200,200,200);
        add(microMap, 1);
    }

    public void showResult(final String result) {

        JPanel victoryPanel = new JPanel(new GridBagLayout());
        victoryPanel.setBounds(0, 0, (int) MainFrame.DIMENSION.getWidth(), (int) MainFrame.DIMENSION.getHeight());
        victoryPanel.setBackground(new Color(0, 0, 0, 0));

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
    }

    private void updatePosition(final Direction dir){
        switch (dir){
            case UP: currentY = currentY-1; break;
            case DOWN: currentY = currentY+1; break;
            case LEFT: currentX = currentX-1; break;
            case RIGHT: currentX = currentX+1; break;
        }
    }

}
