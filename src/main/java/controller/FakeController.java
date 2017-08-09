package controller;

import client.controller3.ControllerCharacter;
import client.model.Direction;
import client.model.MatchResult;
import client.model.MatchResultImpl;
import client.model.Playground;
import client.model.character.Character;
import client.model.gameElement.GameItem;
import client.model.utils.Dimension;
import client.model.utils.Point;
import client.model.utils.PointImpl;
import client.utils.IOUtils;
import client.view.*;
import client.view.playground.PlaygroundBuilderImpl;
import client.view.playground.PlaygroundPanel;
import client.view.playground.PlaygroundView;
import scala.collection.mutable.Map;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class FakeController implements ControllerCharacter{

    private CreateTeamDialog ct;
    private Playground playground;
    private int currentX = 30;
    private int currentY = 8; //TODO CAMBIA
    private final CharacterView characterView = new PacmanView();

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

        playground = IOUtils.getPlaygroundFromFile(new File(nameFile));

        PlaygroundView view = new PlaygroundBuilderImpl()
                .setColumns(playground.dimension().x())
                .setRows(playground.dimension().y())
                .setBackground(Color.black)
                .createPlayground();

        view.renderBlockList(Utils.getJavaList(playground.blocks()));
        view.renderEatableList(Utils.getJavaList(playground.eatables()));

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

    public void startMatch(Map<Character, String> players, Character character, Dimension playgroundDimention, scala.collection.immutable.List<GameItem> ground) {

    }

    public Point<Object, Object> move(Direction direction) {

        if(checkPosition(direction)){

            ((GamePanelImpl)MainFrame.getInstance().getContentPane()).move(characterView, direction);

            switch (direction){
                case UP:  currentY = currentY-1;break;
                case DOWN: currentY = currentY+1;break;
                case LEFT: currentX = currentX-1;break;
                case RIGHT: currentX = currentX+1;break;
            }
        }
        return new PointImpl<Object, Object>(currentX,currentY); //TODO non serve
    }

    public void startGame(){
        //dovrà chiamare un metodo del model che gli restituisce tutti i dati della partita x inizializzare il playground
        // e i personaggi del gioco

        PlaygroundPanel playgroundView = initializePlaygroundView("src/main/resources/playground/alex.dpac", null );//model.getCharacterList());
        MainFrame.getInstance().setContentPane(playgroundView);
        GamePanelImpl gp = new GamePanelImpl(playgroundView);

        MainFrame.getInstance().setContentPane(gp);

        UserInputController keyboardController = new UserInputController(this);
        playgroundView.addKeyListener(keyboardController);
        gp.move(characterView, Direction.RIGHT);
    }

    public void victory(){
        showResult("VICTORY!");
    }

    public void defeat(){
        showResult("DEFEAT...");
    }

    private void showResult(final String result){
        if(MainFrame.getInstance().getContentPane() instanceof GamePanelImpl) {
            ((GamePanelImpl)MainFrame.getInstance().getContentPane()).showResult(result);
        }
    }

    private boolean checkPosition(final Direction direction){
        switch (direction){
            case UP: return (currentY-1) >= 0 && (currentY-1) < playground.dimension().y();
            case DOWN: return (currentY+1) >= 0 && (currentY+1) < playground.dimension().y();
            case LEFT: return (currentX-1) >= 0 && (currentX-1) < playground.dimension().x();
            case RIGHT: return (currentX+1) >= 0 && (currentX+1) < playground.dimension().x();
        }

        return false;
    }

    public Playground getPlayground(){
        return IOUtils.getPlaygroundFromFile(new File("src/main/resources/playground/alex.dpac"));
    }
}
