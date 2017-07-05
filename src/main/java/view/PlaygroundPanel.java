package view;

import client.gameElement.*;
import client.utils.Dimension;
import client.utils.Position;
import view.utils.ImagesUtils;

import java.awt.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class strengthens the BasePlayground
 * Created by Manuel Bottax and chiaravarini on 04/07/2017.
 */

//PimpMyLibrary pattern?
public class PlaygroundPanel extends BasePlaygroundPanel {  //TODO rinominare

    public PlaygroundPanel(Dimension playgroundDimension){
        super(playgroundDimension);
    }

    /**
     * Shows all labyrinth blocks in their specified position
     * @param blockList to render
     */
    public void renderBlockList(List<Block> blockList){
        for ( Block b : blockList) {
            super.renderBlock((int) b.position().x(), (int) b.position().y(), chooseBlockImage(b, blockList));
        }
    }

    /**
     * Shows all etable elemnts (dots and fruits) in the specified position
     * @param eatableList to render
     */
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

    private boolean lookAtLeft(Block block, List<Block> blockList){
        return lookAt(blockList,  p->((int)p.x() == (int) block.position().x()-1 && (int)p.y() == (int) block.position().y()));
    }

    private boolean lookAtRight(Block block, List<Block> blockList){
        return lookAt(blockList,  p->((int)p.x() == (int) block.position().x()+1 && (int)p.y() == (int) block.position().y()));
    }

    private boolean lookAtTop(Block block, List<Block> blockList){
        return lookAt(blockList,  p->((int)p.x() == (int) block.position().x() && (int)p.y() == (int) block.position().y()-1));
    }

    private boolean lookAtBottom(Block block, List<Block> blockList){
        return lookAt(blockList,  p->((int)p.x() == (int) block.position().x() && (int)p.y() == (int) block.position().y()+1));
    }

    private boolean lookAt(List<Block> blockList, Predicate<Position> predicate){  //Strategy
        return blockList
                .stream()
                .map(b->b.position())
                .filter(predicate)
                .collect(Collectors.toList())
                .size() >= 1;
    }


    private Image chooseBlockImage(Block block, List<Block> list){

        boolean isHorizontal = lookAtLeft(block,list) && lookAtRight(block,list) && !lookAtTop(block,list) && !lookAtBottom(block,list);
        boolean isLeftEnd = !lookAtLeft(block,list) && lookAtRight(block,list) && !lookAtTop(block,list) && !lookAtBottom(block,list);
        boolean isLeftOpen = lookAtLeft(block,list) && !lookAtRight(block, list) && lookAtTop(block,list) && lookAtBottom(block,list);
        boolean isLowerEnd = !lookAtLeft(block,list) && !lookAtRight(block,list) && lookAtTop(block,list) && !lookAtBottom(block,list);
        boolean isLowerLeftEdge = !lookAtLeft(block,list) && lookAtRight(block,list) && lookAtTop(block,list) && !lookAtBottom(block,list);
        boolean isLowerOpen = lookAtLeft(block,list) && lookAtRight(block,list) && !lookAtTop(block,list) && lookAtBottom(block,list);
        boolean isLowerRightEdge = lookAtLeft(block,list) && !lookAtRight(block,list) && lookAtTop(block,list) && !lookAtBottom(block,list);
        boolean isRightEnd = lookAtLeft(block,list) && !lookAtRight(block,list) && !lookAtTop(block,list) && !lookAtBottom(block,list);
        boolean isRightOpen = !lookAtLeft(block,list) && lookAtRight(block,list) && lookAtTop(block,list) && lookAtBottom(block,list);
        boolean isUpperEnd = !lookAtLeft(block,list) && !lookAtRight(block,list) && !lookAtTop(block,list) && lookAtBottom(block,list);
        boolean isUpperLeftEdge = !lookAtLeft(block,list) && lookAtRight(block,list) && !lookAtTop(block,list) && lookAtBottom(block,list);
        boolean isUpperOpen = lookAtLeft(block,list) && lookAtRight(block,list) && lookAtTop(block,list) && !lookAtBottom(block,list);
        boolean isUpperRightEdge = lookAtLeft(block,list) && !lookAtRight(block,list) && !lookAtTop(block,list) && lookAtBottom(block,list);
        boolean isVertical = !lookAtLeft(block,list) && !lookAtRight(block,list) && lookAtTop(block,list) && lookAtBottom(block,list);

        BlockView blockViwer = new BlockViewImpl();

        if(isHorizontal){
            return blockViwer.getHorizontal();

       } else if(isLeftEnd){
            return  blockViwer.getLeftEnd();

        }else if(isLeftOpen){
           return blockViwer.getLeftUpLower();

       }else if(isLowerEnd){
            return blockViwer.getLowerEnd();

        }else if(isLowerLeftEdge){
           return blockViwer.getLowerLeftEdge();

        }else if(isLowerOpen){
            return blockViwer.getLeftRightLower();

        }else if(isLowerRightEdge){
            return blockViwer.getLowerRightEdge();

        }else if(isRightEnd){
            return blockViwer.getRightEnd();

        }else if(isRightOpen){
            return blockViwer.getUpRightLower();

        }else if(isUpperEnd) {
            return blockViwer.getUpperEnd();

        }else if(isUpperLeftEdge){
            return blockViwer.getUpperLeftEdge();

        }else if(isUpperOpen){
            return blockViwer.getLeftUpperRight();

        }else if(isUpperRightEdge){
            return blockViwer.getUpperRightEdge();

        }else if(isVertical){
            return blockViwer.getVertical();
        }else {
            return null; //TODO caricare immagine default quadratino blu
        }
    }
}
