package view.utils;

import client.gameElement.Fruits;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by chiaravarini on 03/07/17.
 */
public class ImagesUtils {

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public static FruitsImages getFruitsImage (Fruits type){
        switch (type){
            case CHERRY:
                return FruitsImages.CHERRY;

            case STRAWBERRY:
                return FruitsImages.STRAWBERRY;

            case ORANGE:
                return FruitsImages.ORANGE;

            case APPLE:
                return FruitsImages.APPLE;

            case GRAPES:
                return FruitsImages.GRAPES;

            case GALAXIAN_SHIP:
                return FruitsImages.GALAXIAN;

            case BELL:
                return FruitsImages.BELL;

            case KEY:
                return FruitsImages.KEY;

        }

        return null;
    }
}
