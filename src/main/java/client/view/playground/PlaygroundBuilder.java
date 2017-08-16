package client.view.playground;

import java.awt.*;

/**
 * Builder for a PlaygroundView where there are all the methods
 * for setting the different components of a PlaygroundView
 *
 * Created by manuBottax and Chiara Varini on 05/07/17.
 */
public interface PlaygroundBuilder {

    /**
     *Set the number of playground columns
     * @param columns
     */
    PlaygroundBuilder setColumns(final int columns);

    /**
     *Set the number of playground rows
     * @param rows
     */
    PlaygroundBuilder setRows(final int rows);

    /**
     *Set the background color of playground
     * @param backgroundColor
     */
    PlaygroundBuilder setBackground(final Color backgroundColor);

    /**
     *Set the background image of playground
     * @param backgroundImage
     */
    PlaygroundBuilder setImageBackground(final Image backgroundImage);

    /**
     * Creates a PlaygroundView with the specified parameters
     * @return a PlaygroundView
     */
    PlaygroundView createPlayground();

}
