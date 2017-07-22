package controller;

import client.controller.Controller;
import client.controller.UserInputController;
import client.model.Direction;
import client.model.MatchResult;
import client.model.MatchResultImpl;
import client.model.Playground;
import client.model.character.Character;
import client.model.gameElement.GameItem;
import client.model.utils.Dimension;
import client.model.utils.Point;
import client.utils.IOUtils;
import client.view.*;
import client.view.playground.GamePanel;
import client.view.playground.PlaygroundBuilderImpl;
import client.view.playground.PlaygroundPanel;
import client.view.playground.PlaygroundView;
import scala.collection.mutable.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static client.view.utils.JComponentsUtils.BACKGROUND_COLOR;
import static client.view.utils.JComponentsUtils.FONT_SIZE;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class FakeController implements Controller{

    private CreateTeamDialog ct;

    public List<MatchResult>  getmatches(){
        List<MatchResult> r = new ArrayList<>();
        r.add(new MatchResultImpl(true, 210));
        r.add(new MatchResultImpl(false, 410));
        r.add(new MatchResultImpl(true, 6210));
        r.add(new MatchResultImpl(true, 110));
        r.add(new MatchResultImpl(false, 10));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        return  r;
    }

    public boolean login(String user, String pass){
        return true;

    }

    public void exit(String username){

    }

    public boolean registration(String n, String s, String e, String u, String p){

        return  true; //RegistrationResult
    }

    public List<String> getTeamRange(){
        return new ArrayList<String>(Arrays.asList("3-5", "5-10", "10-15"));
    }


    public PlaygroundPanel initializePlaygroundView (String nameFile, List<Character> characterList) {

        Playground playground = IOUtils.getPlaygroundFromFile(nameFile);

        PlaygroundView view = new PlaygroundBuilderImpl()
                .setColumns(playground.dimension().x())
                .setRows(playground.dimension().y())
                .setBackground(Color.black)
                .createPlayground();

        view.renderBlockList(Utils.getJavaList(playground.blocks()));
        view.renderEatableList(Utils.getJavaList(playground.eatables()));

        //view.renderCharacter( 45, 17, new CharacterFactory().createPacman() , "left");

        return (PlaygroundPanel)view;
    }

    public void sendRequest(String username){

    }

    public java.util.Map<Image,String> getAllCharactersImagesJava(){

        HashMap<Image,String> map = new HashMap<>();
        map.put((new PacmanView().getCharacterRight()),"PACMAN");
        map.put((new RedGhostView().getCharacterRight()), "RED");
        map.put((new BlueGhostView().getCharacterRight()), "BLUE");
        map.put((new PinkGhostView().getCharacterRight()), "PINK");
        map.put((new YellowGhostView().getCharacterRight()), "YELLOW");
        map.put((new RedGhostView().getCharacterRight()), "RED");
        map.put((new BlueGhostView().getCharacterRight()), "BLUE");
        map.put((new PinkGhostView().getCharacterRight()), "PINK");
        map.put((new YellowGhostView().getCharacterRight()), "YELLOW");
        map.put((new RedGhostView().getCharacterRight()), "RED");
        map.put((new BlueGhostView().getCharacterRight()), "BLUE");
        map.put((new PinkGhostView().getCharacterRight()), "PINK");
        map.put((new YellowGhostView().getCharacterRight()), "YELLOW");
        return map;
    }

    public Map<Image,String> getAllCharactersImages(){return null;}

    public java.util.Map<Image,String> getAllPlaygroundsImages(){
        HashMap<Image,String> map = new HashMap<>();
        map.put(Utils.getImage("/mazes/arancione"), "ARANCIONE");
        map.put(Utils.getImage("/mazes/blu"), "BLU");
        map.put(Utils.getImage("/mazes/rosa"), "ROSA");
        map.put(Utils.getImage("/mazes/rosso"), "ROSSO");
        map.put(Utils.getImage("/mazes/rossoGiallo"), "GIALLO");
        map.put(Utils.getImage("/mazes/verde"), "VERDE");
        map.put(Utils.getImage("/mazes/viola"), "VIOLA");
        map.put(Utils.getImage("/mazes/viola2"), "VIOLA2");
        map.put(Utils.getImage("/mazes/arancione"), "ARANCIONE");
        return map;
    }

    public String getUsername(){
        return "CHIA";
    }

    @Override
    public void startMatch(Map<Character, String> players, Character character, Dimension playgroundDimention, scala.collection.immutable.List<GameItem> ground) {

    }

    @Override
    public Point<Object, Object> move(Direction direction) {
        return null;
    }

    public void startGame(){
        //dovrà chiamare un metodo del model che gli restituisce tutti i dati della partita x inizializzare il playground
        // e i personaggi del gioco

        PlaygroundPanel playgroundView = initializePlaygroundView("default.dpac", null );//model.getCharacterList());
        GamePanel gp = new GamePanel();
        gp.addPlayground(playgroundView);
        MainFrame.getInstance().setContentPane(playgroundView);

        playgroundView.renderCharacter(30, 30, new CharacterFactory().createPacman(), "left");

        UserInputController keyboardController = new UserInputController(playgroundView);
        playgroundView.addKeyListener(keyboardController);
        defeat();
    }

    public void victory(){
        showResult1("VICTORY!");
    }

    public void defeat(){
        showResult1("DEFEAT...");
    }

    private void showResult(final String result){
        if(MainFrame.getInstance().getContentPane() instanceof GamePanel) {
            ((GamePanel)MainFrame.getInstance().getContentPane()).showResult(result);
        }
    }

    private void showResult1(final String result){
        JLayeredPane p = new JLayeredPane();
        p.add(MainFrame.getInstance().getContentPane(), 0);

        JPanel victoryPanel = new JPanel(new GridBagLayout());
        victoryPanel.setBounds(0, 0,(int) MainFrame.DIMENSION.getWidth(), (int) MainFrame.DIMENSION.getHeight());
        victoryPanel.setBackground(new Color(0,0,0,0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel l = new JLabel(result);
        l.setForeground(BACKGROUND_COLOR);
        l.setFont(new Font(l.getFont().getName(), Font.BOLD, FONT_SIZE*3));

        victoryPanel.add(l, gbc);
        p.add(victoryPanel, 1);

        MainFrame.getInstance().setContentPane(p);
    }




}
