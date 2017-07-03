package view;

import client.VirtualPlayground;
import client.gameElement.*;
import client.utils.Point;
import client.utils.Position;
import scala.Int;
import scala.Option;
import view.utils.Fruits;
import view.utils.ImagesUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the visual representation of the playground
 *
 * Created by Manuel Bottax on 01/07/2017.
 */
public class PlaygroundPanel extends JPanel{

    private class Pair {
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        @Override
        public boolean equals(Object obj) {
            Pair p = null;
            if (obj instanceof Pair) {
                p = (Pair) obj;
            }
            System.out.println(p != null);
            System.out.println(p.getX() == this.x);
            System.out.println(p.getY() == this.y);
            return (p != null && p.getX() == this.x && p.getY() == this.y);
        }
    }

    private static final int DEFAULT_LABEL_DIMENSION = 24;
    private int labelDimension = DEFAULT_LABEL_DIMENSION;

    // inizializzazione delle view dei vari elementi
    private BlockView blocksImages = new BlockViewImpl();
    private GameObjectView gameObjectImages = new GameObjectViewImpl();
    // c'è un modo migliore di hashmap + add ?
    private Map<String,CharacterView> characterViewMap= new HashMap<>();

    private Map<Integer,JLabel> elementMap = new HashMap<>();

    private GridBagConstraints gbc;

    public PlaygroundPanel(int xDimension, int yDimension){

        System.out.println(xDimension + " - " + yDimension);

        this.setBackground(new Color(0,0,0));

        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = xDimension;
        gbc.gridheight = yDimension;

        for (int i = 0; i < xDimension; i ++){
            for (int j = 0; j < yDimension; j++){
                JLabel l = new JLabel ();
                l.setMinimumSize(new Dimension(labelDimension,labelDimension));
                gbc.gridx = i;
                gbc.gridy = j;

                this.add(l,gbc);
                this.elementMap.put((i * xDimension) + j, l);
            }
        }



    }

    public PlaygroundPanel(int xDimension, int yDimension, int resolution){
        this(xDimension,yDimension);
        this.labelDimension = resolution;
    }

    public void renderBlock(int x, int y){

        System.out.println("Sono un block !");
        System.out.println("x: " + x + " - y: " + y);
        JLabel l = elementMap.get((x * 40) + y);
        //l.setMinimumSize(new Dimension(labelDimension,labelDimension));
        System.out.println("elemList size: " + elementMap.size());
        ImageIcon img = getImageIcon(blocksImages.getHorizontal());
        l.setIcon(img);

        //gbc.gridx = x;
        //gbc.gridy = y;

        //this.add(l,gbc);
    }

    public void renderDot(int x, int y){

        System.out.println("Sono un dot !");
        System.out.println("x: " + x + " - y: " + y);
        //JLabel l = new JLabel ();
        //l.setMinimumSize(new Dimension(labelDimension,labelDimension));
        JLabel l = elementMap.get((x * 40) + y);
        ImageIcon img = getImageIcon(gameObjectImages.getDot());
        l.setIcon(img);

        //gbc.gridx =  x;
        //gbc.gridy =  y;

        this.add(l,gbc);
    }

    public void renderPill(int x, int y){
        System.out.println("Sono una pill !");
        JLabel l = new JLabel ();
        l.setMinimumSize(new Dimension(labelDimension,labelDimension));
        ImageIcon img = getImageIcon(gameObjectImages.getPill());
        l.setIcon(img);

        gbc.gridx = x;
        gbc.gridy = y;

        this.add(l,gbc);
    }

    public void renderFruit(int x, int y, Fruits type){
        System.out.println("Sono un " + type.name() + " !");
        JLabel l = new JLabel ();
        l.setMinimumSize(new Dimension(labelDimension,labelDimension));
        ImageIcon img = getImageIcon(gameObjectImages.getFruit(type));
        l.setIcon(img);

        gbc.gridx = x;
        gbc.gridy = y;

        this.add(l,gbc);
    }

    public void renderCharacter(int x, int y, String name, String direction){
        System.out.println("Sono " + name + " !");
        JLabel l = new JLabel ();
        l.setMinimumSize(new Dimension(labelDimension,labelDimension));
        CharacterView characterView = this.characterViewMap.get(name);
        if (characterView != null) {
            ImageIcon img = getImageIcon(characterView.getCharacterLeft());
            switch(direction){
                case "up" :
                    img = getImageIcon(characterView.getCharacterUp());
                    break;
                case "down" :
                    img = getImageIcon(characterView.getCharacterDown());
                    break;
                case "right" :
                    img = getImageIcon(characterView.getCharacterRight());
                    break;
            }

            l.setIcon(img);

            gbc.gridx = x;
            gbc.gridy = y;

            this.add(l, gbc);
        }
    }


    public void addCharacterView(String name /*Character.name() del model*/) {
        if (name.equals("pacman"))
            this.characterViewMap.put(name, new CharacterViewImpl(new CharacterPathImpl(name)));
        else
            this.characterViewMap.put(name, new CharacterViewImpl(new GhostPath(name)));
    }

    private ImageIcon getImageIcon(final Image image){
        return new ImageIcon(ImagesUtils.getScaledImage(image, labelDimension, labelDimension));
    }




    /*

    private static final int LABEL_DIMENSION = 24;

    private VirtualPlayground playground;

    private BlockView blocksImages = new BlockViewImpl();
    private GameObjectView gameObjectImages = new GameObjectViewImpl();

    public PlaygroundPanel (VirtualPlayground playground){
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
                            img = new ImageIcon(Utils.getResource("/images/pill/YellowPill.png")); //TODO pensare una size "definita" (??)
                            //img =getImageIcon(gameObjectImages.getPill());
                            System.out.println("Img size : " + img.getIconHeight() + " | " + img.getIconWidth());
                            System.out.println("position : " + i + " | " + j);
                            l.setIcon(img);
                            break;

                        case Fruit :
                            System.out.println("Sono un fruit !");

                            switch ( ((VirtualFruit) it.get()).fruitTypes()){
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
                                    break; //TODO lanciare eccezione
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


    */



}
