package view;

import client.gameElement.*;
import client.utils.Dimension;
import view.utils.ImagesUtils;

import java.util.List;

/**
 * Created by Manuel Bottax on 04/07/2017.
 */
public class BulkPlaygroundPanel extends PlaygroundPanel {

    public BulkPlaygroundPanel (Dimension playgroundDimension){
        super(playgroundDimension);
    }

    public void renderBlockList(List<Block> blockList){
        for ( Block b : blockList) {
            super.renderBlock((int) b.position().x(), (int) b.position().y());
        }
    }

    public void renderEatableList(List<Eatable> eatableList){
        for (Eatable e : eatableList){
            if (e instanceof Dot){
                super.renderDot((int) e.position().x(), (int) e.position().y());
            }
            else if (e instanceof Pill) {
                super.renderPill((int) e.position().x(), (int) e.position().y());
            }
            else if (e instanceof Fruit) {
                super.renderFruit((int) e.position().x(), (int) e.position().y(), ImagesUtils.getFruitsImage(((Fruit) e).fruitTypes()));      //TODO passare dal tipo alla enum delle immagini
            }
        }
    }
}
