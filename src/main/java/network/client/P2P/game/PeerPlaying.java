package network.client.P2P.game;

import client.model.Match;
import client.model.MatchImpl;
import client.model.character.Character;
import client.model.peerCommunication.ClientOutcomingMessageHandler;
import client.model.peerCommunication.ClientOutcomingMessageHandlerImpl;
import network.client.P2P.bootstrap.PeerStateRegister;

import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Federica on 27/07/17.
 */
public class PeerPlaying implements Callable, Remote {

    private Match match;
    private Character character;
    private int rmiPort;
    private Registry registry;
    private ClientOutcomingMessageHandler handler;
    private Map<String, PeerPlaying> objects;


    public PeerPlaying(){}

    public PeerPlaying(int rmiPort, Registry registry){
        this.match = MatchImpl.instance();
        this.character = match.myCharacter();
        this.rmiPort = rmiPort;
        this.registry = registry;
        this.handler = new ClientOutcomingMessageHandlerImpl();
        this.objects = new HashMap<>();
    }

    @Override
    public Object call() throws Exception {
        PeerStateRegister stub;

        objects.put("positon", new PeerPlaying());
        objects.put("score", new PeerPlaying());
        objects.put("remaningLives", new PeerPlaying());
        objects.put("death", new PeerPlaying());

        while(character.isAlive()){

            if(character){
                stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("positon"), this.rmiPort);
                registry.rebind("Position", stub);
                handler.notifyMove(character);
            }else if(){
                stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("score"), this.rmiPort);
                registry.rebind("Score", stub);
                handler.notifyScore(character);
            }else if(){
                stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("remaningLives"), this.rmiPort);
                registry.rebind("RemaningLives", stub);
                handler.notifyRemainingLives(character);
            }else if(){
                stub = (PeerStateRegister) UnicastRemoteObject.exportObject(objects.get("death"), this.rmiPort);
                registry.rebind("Death", stub);
                handler.notifyDeath(character);
            }
        }

        return null;
    }





}
