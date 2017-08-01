package network.client.P2P.game;

import client.model.Match;
import client.model.MatchImpl;
import client.model.character.Character;
import client.model.peerCommunication.ClientOutcomingMessageHandler;
import client.model.peerCommunication.ClientOutcomingMessageHandlerImpl;
import client.model.utils.Point;
import network.client.P2P.bootstrap.ServerBootstrap;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Federica on 27/07/17.
 */
public class ServerPlayingWorkerThread implements Runnable, PeerRegister {

    private int rmiPort;
    private Registry registry;
    private Map<String, ServerPlayingWorkerThread> objects;

    private Match match;
    private Character character;
    private Point<Object,Object> currentPosition;
    private int currentScore;
    private String currentLives;
    private Boolean isAlive;

    private ClientOutcomingMessageHandler handler;



    public ServerPlayingWorkerThread(){}

    public ServerPlayingWorkerThread(int rmiPort){
        this.match = MatchImpl.instance();
        //this.character = match.myCharacter(); //TODO SCOMMENTARE QUANDO PUSHO SU DEVELOP
        this.rmiPort = rmiPort;
        this.registry = ServerBootstrap.getRegistry();
        this.handler = new ClientOutcomingMessageHandlerImpl();
        this.currentPosition = character.position();
        this.currentScore = character.score();
        this.currentLives = character.lives().toString();
        this.isAlive = character.isAlive();
        this.objects = new HashMap<>();

    }

    @Override
    public Point<Object, Object> getPosition() {
        return this.currentPosition;
    }

    @Override
    public int getScore() {
        return this.currentScore;
    }

    @Override
    public String getLives() {
        return this.currentLives;
    }

    @Override
    public Boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void run() {
        initializeObjectBinding();

        //per far terminare questo while basta chiamare shutdown
        while (!Thread.currentThread().isInterrupted()) {
            try {
                updateObjects();
                wait(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    private void initializeObjectBinding(){
        PeerRegister stub;

        //TO DO WRAPPER FOR STRING
        this.objects.put("currentPosition", new ServerPlayingWorkerThread());
        this.objects.put("currentScore", new ServerPlayingWorkerThread());
        this.objects.put("currentLives", new ServerPlayingWorkerThread());
        this.objects.put("isAlive", new ServerPlayingWorkerThread());

        for(Map.Entry<String, ServerPlayingWorkerThread> pair: objects.entrySet()){
            try {
                stub = (PeerRegister) UnicastRemoteObject.exportObject(pair.getValue(), this.rmiPort);
                registry.bind(pair.getKey(), stub);

            } catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Server ready");

    }


    private void updateObjects() throws RemoteException {
        if(!character.position().equals(currentPosition)){
            registry.rebind("currentPosition", objects.get("currentPosition"));
            this.currentPosition = character.position();
            handler.notifyMove(character);

        }else if(!((Integer) character.score()).equals(currentScore)){
            registry.rebind("currentScore", objects.get("currentScore"));
            this.currentScore = character.score();
            handler.notifyScore(character);

        }else if(!character.lives().equals(currentLives)){
            registry.rebind("currentLives", objects.get("currentLives"));
            this.currentLives = character.lives().toString();
            handler.notifyRemainingLives(character);
            //handler.notifyRemainingLives("remainingLives", character);

        }else if(!character.isAlive() == isAlive){
            registry.rebind("isAlive", objects.get("isAlive"));
            this.isAlive = character.isAlive();
            handler.notifyDeath(character);

        }

    }



}
