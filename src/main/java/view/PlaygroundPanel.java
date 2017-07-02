package view;

import client.Playground;
import client.gameElement.GameItem;
import client.utils.*;
import client.utils.Point;
import scala.Int;
import scala.Option;

import javax.swing.*;
import java.awt.*;

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
                l.setSize(24,24);


                Option<GameItem> it = playground.getElementAtPosition(new Point (i,j));
                if (! it.isEmpty()){
                    switch( it.get().itemType() ) {
                        case "block" :
                        System.out.println("Sono un block !");
                        ImageIcon img = new ImageIcon(Utils.getResource("/images/block/24x24/horizontalBlock.png"));
                        System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                        System.out.println("position : " + i + " | " + j);
                        l.setIcon(img);
                        break;

                        case "dot" :
                            System.out.println("Sono un dot !");
                            img = new ImageIcon(Utils.getResource("/images/dot/YellowDot.png"));
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case "pill" :
                            System.out.println("Sono una pill !");
                            img = new ImageIcon(Utils.getResource("/images/pill/YellowPill.png"));
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        //....


                    }
                }

                this.add(l);
            }
        }

        this.repaint();
        this.setVisible(true);
    }




}
