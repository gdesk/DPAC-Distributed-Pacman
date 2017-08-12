package client.view.playground;

import java.awt.*;

/**
 * Builder for a PlaygroundView where there are all the methods
 * for setting the different components of a PlaygroundView
 *
 * Created by manuBottax and Chiara Varini on 05/07/17.
 */
public interface PlaygroundBuilder {

    PlaygroundBuilder setColumns(final int columns);
    PlaygroundBuilder setRows(final int rows);
    PlaygroundBuilder setBackground(final Color backgroundColor);
    PlaygroundBuilder setBackground(final Image backgroundImage);
    PlaygroundView createPlayground();

}
