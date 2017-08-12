package client.view.playground;

import client.model.gameElement.Block;
import client.model.gameElement.Eatable;

import java.util.List;

/**
 * A Playground where you can view
 * all the labyrinths and elements in one shot
 * Created by Chiara Varini on 06/07/17.
 */
public interface PlaygroundView extends BasePlaygroundView {

    /**
     * Renders all block present in the blocksList
     * @param blocksList
     */
    void renderBlockList(final List<Block> blocksList);

    /**
     * Renders all etable elements present in the eatableList
     * @param eatableList
     */
    void renderEatableList(final List<Eatable> eatableList);

}
