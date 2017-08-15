package network.client.P2P.game;

import client.model.Direction;
import client.model.Match;
import client.model.MatchImpl;
import client.model.character.Character;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
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
public class ServerPlayingWorkerThread implements Runnable {

    private ExecutorServiceUtility executor;
    private int rmiPort;
    private Registry registry;
    private Map<String, ServerObjects> objects;

    private Match match;
    private Character character;
    private String characterName;
    private Direction direction;
    private Boolean isAlive;

    private static ServerPlayingWorkerThread SINGLETON = null;


    private ServerPlayingWorkerThread(){
        this.character = MatchImpl.myCharacter();
        this.characterName = MatchImpl.myCharacter().name();

        this.direction = character.direction();
        this.isAlive = character.isAlive();
        this.objects = new HashMap<>();

    }

    public static ServerPlayingWorkerThread getIstance(ExecutorServiceUtility executor, Registry registry, int rmiPort){
        if(SINGLETON == null){
            SINGLETON = new ServerPlayingWorkerThread();
            SINGLETON.init(executor, registry, rmiPort);
        }
        return SINGLETON;
    }

    public Direction getDirection() {
        return this.direction;
    }


    public Boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void run() {

        System.out.println("ServerPlayingWorkerThread");
        initializeObjectBinding();

        //per far terminare questo while basta chiamare shutdown
        while (!Thread.currentThread().isInterrupted()) {
            try {
                updateObjects();
             //   wait(1000);
           // } catch (InterruptedException ex) {
           //     Thread.currentThread().interrupt();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }


    private void initializeObjectBinding(){
        Remote stub;

        this.objects.put("direction", new ServerObjects());
        this.objects.put("isAlive", new ServerObjects());

        int i = 0;

        for(Map.Entry<String, ServerObjects> pair: objects.entrySet()){
            try {
                System.out.println("entro nel for" + i++);
                stub =  UnicastRemoteObject.exportObject(pair.getValue(), this.rmiPort);
                registry.bind(pair.getKey(), stub);

            } catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Server ready");

    }


    private void updateObjects() throws RemoteException {
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
