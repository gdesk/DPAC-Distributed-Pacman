package network.client.P2P.game;

import client.model.Direction;
import client.model.Match;
import client.model.MatchImpl;
import client.model.character.Character;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Federica on 27/07/17.
 * This class is useful to update local values
 * relating to character on my peer
 * (so that other peers can take updated values
 * when they need to refresh character info in their gui)
 */
public class ServerPlayingWorkerThread implements Runnable, PeerRegister {

    private ExecutorServiceUtility executor;
    private int rmiPort;
    private Registry registry;
    private Map<String, ServerPlayingWorkerThread> objects;

    private Match match;
    private Character character;
    private String characterName;
    private Direction direction;
    private Boolean isAlive;

    private static ServerPlayingWorkerThread SINGLETON = null;


    private ServerPlayingWorkerThread(){
        this.match = MatchImpl.instance();
        this.character = match.myCharacter();
        this.characterName =  match.myCharacter().name();

        this.direction = character.direction();
        this.isAlive = character.isAlive();
        this.objects = new HashMap<>();

    }

    public static ServerPlayingWorkerThread getIstance(ExecutorServiceUtility executor, String ip, Registry registry, int rmiPort){
        if(SINGLETON == null){
            SINGLETON = new ServerPlayingWorkerThread();
            SINGLETON.init(executor, registry, rmiPort);
        }
        return SINGLETON;
    }

    public Direction getDirection() {
        return this.direction;
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

        //this.objects.put("currentPosition", new ServerPlayingWorkerThread());
        this.objects.put("direction", new ServerPlayingWorkerThread());
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
        /*if(!character.position().equals(currentPosition)){
            registry.rebind("currentPosition", objects.get("currentPosition"));
             this.currentPosition = new PointImpl<>(character.position().x(),character.position().y());

        }*/
        if(character.direction().equals(direction)){
            registry.rebind("direction", objects.get("direction"));
            this.direction = character.direction();

        }else if(!character.isAlive() == isAlive){
            registry.rebind("isAlive", objects.get("isAlive"));
            this.isAlive = character.isAlive();
            executor.stopServerPlayingWorkerThread();

        }

    }

    private void init(ExecutorServiceUtility executor, Registry registry, int rmiPort){

        this.executor = executor;
        this.rmiPort = rmiPort;
        this.registry = registry;
    }



}
