package client.view;

import client.model.gameElement.Block;
import client.model.gameElement.Eatable;

import java.util.List;

/**
 * Created by chiaravarini on 06/07/17.
 */
public interface PlaygroundView extends BasePlaygroundView{

    void renderBlockList(final List<Block> blocksList);
    void renderEatableList(final List<Eatable> blocksList);

}
