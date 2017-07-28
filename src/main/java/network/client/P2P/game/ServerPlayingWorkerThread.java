package network.client.P2P.game;

import client.model.Match;
import client.model.MatchImpl;
import client.model.character.Character;
import client.model.character.Lives;
import client.model.peerCommunication.ClientOutcomingMessageHandler;
import client.model.peerCommunication.ClientOutcomingMessageHandlerImpl;
import client.model.utils.Point;
import network.client.P2P.bootstrap.PeerStateRegister;

import java.rmi.AccessException;
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
    private Map<String, ServerPlayingWorkerThread> objects;
    private Point<Object,Object> currentPosition;
    private int currentScore;
    private Lives currentLives;
    private Boolean isDead;


    public ServerPlayingWorkerThread(){}

    public ServerPlayingWorkerThread(int rmiPort, Registry registry){
        this.match = MatchImpl.instance();
        //this.character = match.myCharacter();
        this.rmiPort = rmiPort;
        this.registry = registry;
        this.handler = new ClientOutcomingMessageHandlerImpl();
        this.objects = new HashMap<>();
        this.currentPosition = character.position();
        this.currentScore = character.score();
        this.currentLives = character.lives();
        this.isDead = character.isAlive();

    }

    @Override
    public void run() {
        PeerStateRegister stub;

        objects.put("positon", new ServerPlayingWorkerThread());
        objects.put("score", new ServerPlayingWorkerThread());
        objects.put("remaningLives", new ServerPlayingWorkerThread());
        objects.put("death", new ServerPlayingWorkerThread());

        //per far terminare questo while basta chiamare shutdown
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if(!character.position().equals(currentPosition)){
                    stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("positon"), this.rmiPort);
                    registry.rebind("Position", stub);
                    this.currentPosition = character.position();
                    handler.notifyMove(character);
                }else if(!((Integer) character.score()).equals(currentScore)){
                    stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("score"), this.rmiPort);
                    registry.rebind("Score", stub);
                    this.currentScore = character.score();
                    handler.notifyScore(character);
                }else if(!character.lives().equals(currentLives)){
                    stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("remaningLives"), this.rmiPort);
                    registry.rebind("RemaningLives", stub);
                    this.currentLives = character.lives();
                    handler.notifyRemainingLives(character);
                }else if(!character.isAlive() == isDead){
                    stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("death"), this.rmiPort);
                    registry.rebind("Death", stub);
                    this.isDead = character.isAlive();
                    handler.notifyDeath(character);
                }

                wait(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (AccessException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}
