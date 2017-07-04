package view;

import java.awt.*;
import static view.MainFrame.*;

/**
 * Created by chiaravarini on 04/07/17.
 */
public final class PlaygroundSettings {

    static final Color backgroundColor = new Color(0,0,0);

    static final int cellSize = (int)(DIMENSION.getWidth()/60); //TODO dimensioni diverse in base alla grandezza dello schermo e/o risoluzione?

    static final Dimension cellDim = new Dimension(cellSize,cellSize);

    static final int columns = (int)Math.floor(DIMENSION.getWidth()/cellSize);

    static final int rows = (int)Math.floor(DIMENSION.getHeight()/cellSize)-1; //TODO bho...

}
