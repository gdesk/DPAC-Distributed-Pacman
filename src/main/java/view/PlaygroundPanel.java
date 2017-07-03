package view;

import client.Playground;
import client.gameElement.Fruit;
import client.gameElement.GameItem;
import client.utils.Point;
import scala.Option;
import view.utils.Fruits;
import view.utils.ImagesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * the visual representation of the playground
 *
 * Created by Manuel Bottax on 01/07/2017.
 */
public class PlaygroundPanel extends JPanel{

    private static final int LABEL_DIMENSION = 24;

    private Playground playground;

    private BlockView blocksImages = new BlockViewImpl();
    private GameObjectView gameObjectImages = new GameObjectViewImpl();

    public PlaygroundPanel (Playground playground){
        this.playground = playground;

        this.setLayout(new GridLayout(50,50));
        this.setBackground(new Color(0,0,0));

        for (int i = 0; i < playground.dimension().yDimension(); i ++){
            for (int j = 0; j < playground.dimension().xDimension(); j++){
                JLabel l = new JLabel ();
                //l.setSize(24,24);
                //l.setPreferredSize(new Dimension(24,24));
                l.setMinimumSize(new Dimension(LABEL_DIMENSION,LABEL_DIMENSION));


                Option<GameItem> it = playground.getElementAtPosition(new Point (i,j));
                if (! it.isEmpty()){
                    switch( it.get().itemType() ) {
                        case Block:
                            System.out.println("Sono un block !");
                            // ImageIcon img = new ImageIcon(Utils.getResource("/images/block/24x24/horizontalBlock.png"));
                            ImageIcon img = getImageIcon(blocksImages.getHorizontal());

                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Dot :
                            System.out.println("Sono un dot !");
                            img = new ImageIcon(Utils.getResource("/images/dot/YellowDot.png"));
                            //img = getImageIcon(gameObjectImages.getDot());
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Pill :
                            System.out.println("Sono una pill !");
                            img = new ImageIcon(Utils.getResource("/images/pill/YellowPill.png")); //TODO pensare una size "definita"
                            //img =getImageIcon(gameObjectImages.getPill());
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Fruit :
                            System.out.println("Sono un fruit !");

                            switch ( ((Fruit) it.get()).fruitTypes()){
                                case Cherry :
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/cherry.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.CHERRY));
                                    break;
                                case Strawberry:
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/strawberry.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.STRAWBERRY));
                                    break;
                                case Orange:
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/orange.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.ORANGE));
                                    break;
                                case Apple:
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/apple.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.APPLE));
                                    break;
                                case Grapes:
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/grapes.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.GRAPES));
                                    break;
                                case GalaxianShip:
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/galaxian.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.GALAXIAN));
                                    break;
                                case Bell:
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/bell.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.BELL));
                                    break;
                                case Key:
                                    //img = new ImageIcon(Utils.getResource("/images/fruit/24x24/key.png"));
                                    img = getImageIcon(gameObjectImages.getFruit(Fruits.KEY));
                                    break;
                                default:
                                    //img = new ImageIcon(Utils.getResource("/images/pill/WhitePill.png"));
                                    //img = getImageIcon(gameObjectImages.getDot());
                                    img = getImageIcon(Utils.getImage("pill/WhitePill"));
                                    break; //TODO lanciare eccezione? pensare a un frutto di default?
                            }

                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Pacman:
                            System.out.println("Sono un pacman !");
                            //img = new ImageIcon(Utils.getResource("/images/pacman/24x24/Right.png"));
                            img = getImageIcon(new PacmanView().getCharacterRight());
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Ghost:
                            System.out.println("Sono un ghost !");
                            //img = new ImageIcon(Utils.getResource("/images/ghosts/blue/24x24/Right.png"));
                            img = getImageIcon(new RedGhostView().getCharacterRight());
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;
                    }
                }

                this.add(l);
            }
        }

        this.repaint();
        this.setVisible(true);
    }


    private ImageIcon getImageIcon(final Image image){
        return new ImageIcon(ImagesUtils.getScaledImage(image, LABEL_DIMENSION,LABEL_DIMENSION));
    }



}
