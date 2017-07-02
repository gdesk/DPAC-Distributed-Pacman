package view;

import client.Playground;
import client.gameElement.Fruit;
import client.gameElement.GameItem;
import client.gameElement.ItemType;
import client.utils.*;
import client.utils.Point;
import scala.Int;
import scala.Option;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

/**
 * the visual representation of the playground
 *
 * Created by Manuel Bottax on 01/07/2017.
 */
public class PlaygroundPanel extends JPanel{

    private Playground playground;

    public PlaygroundPanel (Playground playground){
        this.playground = playground;

        this.setLayout(new GridLayout(50,50));
        this.setBackground(new Color(0,0,0));

        for (int i = 0; i < playground.dimension().yDimension(); i ++){
            for (int j = 0; j < playground.dimension().xDimension(); j++){
                JLabel l = new JLabel ();
                //l.setSize(24,24);
                //l.setPreferredSize(new Dimension(24,24));
                l.setMinimumSize(new Dimension(24,24));


                Option<GameItem> it = playground.getElementAtPosition(new Point (i,j));
                if (! it.isEmpty()){
                    switch( it.get().itemType() ) {
                        case Block:
                            System.out.println("Sono un block !");
                            ImageIcon img = new ImageIcon(Utils.getResource("/images/block/24x24/horizontalBlock.png"));
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                        break;

                        case Dot :
                            System.out.println("Sono un dot !");
                            img = new ImageIcon(Utils.getResource("/images/dot/YellowDot.png"));
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Pill :
                            System.out.println("Sono una pill !");
                            img = new ImageIcon(Utils.getResource("/images/pill/YellowPill.png"));
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Fruit :
                            System.out.println("Sono un fruit !");

                            switch ( ((Fruit) it.get()).fruitTypes()){
                                case Cherry :
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/cherry.png"));
                                    break;
                                case Strawberry:
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/strawberry.png"));
                                    break;
                                case Orange:
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/orange.png"));
                                    break;
                                case Apple:
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/apple.png"));
                                    break;
                                case Grapes:
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/grapes.png"));
                                    break;
                                case GalaxianShip:
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/galaxian.png"));
                                    break;
                                case Bell:
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/bell.png"));
                                    break;
                                case Key:
                                    img = new ImageIcon(Utils.getResource("/images/fruit/24x24/key.png"));
                                    break;
                                default:
                                    img = new ImageIcon(Utils.getResource("/images/pill/WhitePill.png"));
                                    break;
                            }

                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Pacman:
                            System.out.println("Sono un pacman !");
                            img = new ImageIcon(Utils.getResource("/images/pacman/24x24/Right.png"));
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Ghost:
                            System.out.println("Sono un ghost !");
                            img = new ImageIcon(Utils.getResource("/images/ghosts/blue/24x24/Right.png"));
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




}
