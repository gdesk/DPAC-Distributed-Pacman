package view;

import client.gameElement.Block;
import client.gameElement.Eatable;

import java.util.List;

/**
 * Created by chiaravarini on 06/07/17.
 */
public interface PlaygroundView extends BasePlaygroundView{

    void renderBlockList(final List<Block> blocksList);
    void renderEatableList(final List<Eatable> blocksList);

}
