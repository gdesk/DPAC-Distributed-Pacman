package network.client.P2P.game;

import client.model.Match;
import client.model.MatchImpl;
import client.model.character.Character;
import client.model.character.Lives;
import client.model.peerCommunication.ClientOutcomingMessageHandler;
import client.model.peerCommunication.ClientOutcomingMessageHandlerImpl;
import client.model.utils.Point;
import network.client.P2P.bootstrap.PeerStateRegister;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Federica on 27/07/17.
 */
public class ServerPlayingWorkerThread implements Runnable, Remote {

    private Match match;
    private Character character;
    private int rmiPort;
    private Registry registry;
    private ClientOutcomingMessageHandler handler;
    private Point<Object,Object> currentPosition;
    private int currentScore;
    private Lives currentLives;
    private Boolean isDead;
    private Map<String, ServerPlayingWorkerThread> objects;


    public ServerPlayingWorkerThread(){}

    public ServerPlayingWorkerThread(int rmiPort, Registry registry){
        this.match = MatchImpl.instance();
        //this.character = match.myCharacter(); //TODO SCOMMENTARE QUANDO PUSHO SU DEVELOP
        this.rmiPort = rmiPort;
        this.registry = registry;
        this.handler = new ClientOutcomingMessageHandlerImpl();
        this.currentPosition = character.position();
        this.currentScore = character.score();
        this.currentLives = character.lives();
        this.isDead = character.isAlive();
        this.objects = new HashMap<>();

    }

    @Override
    public void run() {
        initializeObjectBinding();

        //per far terminare questo while basta chiamare shutdown
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if(!character.position().equals(currentPosition)){
                    registry.rebind("currentPosition", objects.get("currentPosition"));
                    this.currentPosition = character.position();
                    handler.notifyMove(character);
                }else if(!((Integer) character.score()).equals(currentScore)){
                    //stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("currentScore"), this.rmiPort);
                    registry.rebind("currentScore", objects.get("currentScore"));
                    this.currentScore = character.score();
                    handler.notifyScore(character);
                }else if(!character.lives().equals(currentLives)){
                    //stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("currentLives"), this.rmiPort);
                    registry.rebind("currentLives", objects.get("currentLives"));
                    this.currentLives = character.lives();
                    handler.notifyRemainingLives(character);
                }else if(!character.isAlive() == isDead){
                    //stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("isDead"), this.rmiPort);
                    registry.rebind("isDead", objects.get("isDead"));
                    this.isDead = character.isAlive();
                    handler.notifyDeath(character);
                }

                wait(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    private void initializeObjectBinding(){
        PeerStateRegister stub;

        this.objects.put("currentPosition", new ServerPlayingWorkerThread());
        this.objects.put("currentScore", new ServerPlayingWorkerThread());
        this.objects.put("currentLives", new ServerPlayingWorkerThread());
        this.objects.put("isDead", new ServerPlayingWorkerThread());

        for(Map.Entry<String, ServerPlayingWorkerThread> pair: objects.entrySet()){
            try {
                stub = (PeerStateRegister) UnicastRemoteObject.exportObject(pair.getValue(), this.rmiPort);
                registry.bind(pair.getKey(), stub);

            } catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }

        }

    }


}
