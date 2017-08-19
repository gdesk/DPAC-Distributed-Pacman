package network.client.P2P.game;

import client.model.Direction;
import client.model.Match;
import client.model.MatchImpl;
import client.model.PlayerImpl;
import client.model.character.Character;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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
public class ServerPlayingWorkerThread implements PeerRegister, Runnable  {

    private ExecutorServiceUtility executor;
    private int rmiPort;
    private static Registry registry;
    private Map<String, ServerObjects> objects;

    private Match match;
    private Character character;
    private String characterName;
    private Direction direction;
    private Boolean isAlive;

    private static ServerPlayingWorkerThread SINGLETON = null;


    private static ServerPlayingWorkerThread objDir = new ServerPlayingWorkerThread();
    private static ServerPlayingWorkerThread objIsAlive = new ServerPlayingWorkerThread();


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


        try {
            System.out.println("QUI 1");
            registry = LocateRegistry.createRegistry(1099);
            System.out.println("QUI 2");
            System.setProperty("Djava.rmi.server.codebase", "out/");
            System.out.println("QUI 3");
            System.setProperty("Djava.rmi.server.hostname", PlayerImpl.ip());
            System.out.println("QUI 4");

            PeerRegister stubDirection = (PeerRegister) UnicastRemoteObject.exportObject(objDir, 1099);
            System.out.println("QUI 5");
            registry.bind("direction", stubDirection);
            System.out.println("QUI 6");

            PeerRegister stubIsAlive = (PeerRegister) UnicastRemoteObject.exportObject(objIsAlive, 1099);
            System.out.println("QUI 7");
            registry.bind("isAlive", stubIsAlive);
            System.out.println("QUI 8");

            // Bind the remote object's stub in the registry
            //registry = LocateRegistry.getRegistry();
            
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            // e.printStackTrace();
        }

    }


    public void updateObjects() throws RemoteException {
        System.out.println("SONO IN SERVERPLAYINGWORKERTHREAD: dovrebbe essermi arrivato il messaggio di un altro client che si è moso. updateObjects - 3");

        this.direction = character.direction();
        this.isAlive = character.isAlive();

        /*if(!character.direction().equals(direction)){
            this.direction = character.direction();
            //registry.rebind("direction", objDir);

            System.out.println("rebind -> direction, " + objDir.getDirection().getDirection());

        }else if(!character.isAlive() == isAlive){
            this.isAlive = character.isAlive();
            //registry.rebind("isAlive", objIsAlive);
            executor.stopServerPlayingWorkerThread();

            System.out.println("rebind -> isAlive, " + objDir.isAlive().toString());
        }*/

    }

    private void init(ExecutorServiceUtility executor, Registry registry, int rmiPort){

        this.executor = executor;
        this.rmiPort = rmiPort;
        this.registry = registry;
    }



}
