package controller;

import client.model.MatchResult;
import client.model.MatchResultImpl;
import client.model.Playground;
import client.utils.IOUtils;
import client.view.*;
import client.view.playground.PlaygroundBuilderImpl;
import client.view.playground.PlaygroundPanel;
import client.view.playground.PlaygroundView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class FakeController {

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


    public PlaygroundPanel initializePlaygroundView (List<Character> characterList) {
        IOUtils.saveLog("playground created !");
        Playground playground = IOUtils.getPlaygroundFromFile("default.dpac");


        PlaygroundView view = new PlaygroundBuilderImpl()
                .setColumns(playground.dimension().x())
                .setRows(playground.dimension().y())
                .setBackground(Color.black)
                .createPlayground();

        view.renderBlockList(Utils.getJavaList(playground.blocks()));
        view.renderEatableList(Utils.getJavaList(playground.eatables()));

        view.renderCharacter( 45, 17, new CharacterFactory().createPacman() , "left");

        IOUtils.saveLog("playground initialized !");

        return (PlaygroundPanel)view;
    }

    public void sendRequest(String username){

    }

    public List<Image>getAllCharactersImages(){
        return  new ArrayList<Image>(Arrays.asList(new PacmanView().getCharacterRight(),
                new RedGhostView().getCharacterRight(),
                new BlueGhostView().getCharacterRight(),
                new PinkGhostView().getCharacterRight(),
                new YellowGhostView().getCharacterRight(),
                new RedGhostView().getCharacterRight(),
                new BlueGhostView().getCharacterRight(),
                new PinkGhostView().getCharacterRight(),
                new YellowGhostView().getCharacterRight(),
                new RedGhostView().getCharacterRight(),
                new BlueGhostView().getCharacterRight(),
                new PinkGhostView().getCharacterRight(),
                new YellowGhostView().getCharacterRight(),
                new RedGhostView().getCharacterRight(),
                new BlueGhostView().getCharacterRight(),
                new PinkGhostView().getCharacterRight(),
                new YellowGhostView().getCharacterRight()));
    }

    public List<Image>getAllPlaygroundsImages(){
        return  new ArrayList<Image>(Arrays.asList(
                Utils.getImage("/mazes/arancione"),
                Utils.getImage("/mazes/blu"),
                Utils.getImage("/mazes/rosa"),
                Utils.getImage("/mazes/rosso"),
                Utils.getImage("/mazes/rossoGiallo"),
                Utils.getImage("/mazes/verde"),
                Utils.getImage("/mazes/viola"),
                Utils.getImage("/mazes/viola2"),
                Utils.getImage("/mazes/arancione")));
    }

    public String getUsername(){
        return "CHIA";
    }


   /* PlaygroundPanel playgroundView = controller.initializePlaygroundView( null );//model.getCharacterList());
            MainFrame.getInstance().setContentPane(playgroundView);

    client.model.controller.UserInputController keyboardController = new client.model.controller.UserInputController(playgroundView);
            playgroundView.addKeyListener(keyboardController);*/

}
