package client.view.playground;

import client.model.Playground;
import client.view.MainFrame;

import javax.swing.*;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class MicroMapPanel extends JPanel {

    private final static int BOUND = 30;

    public MicroMapPanel(Playground playground){
        int shortedSide = (int)Math.min(MainFrame.DIMENSION.getWidth(), MainFrame.DIMENSION.getHeight());
        setSize((int) shortedSide/5, (int)shortedSide/5);
        setBounds((int) MainFrame.DIMENSION.getWidth()-getWidth()-BOUND, (int) MainFrame.DIMENSION.getHeight()-getHeight()-BOUND,getWidth(),getHeight());

        int columns = playground.dimension().x();
        int rows = playground.dimension().y();

        int columnsWidth = getWidth()/columns;
        int rowsWidth = getHeight()/rows;



    }

}
