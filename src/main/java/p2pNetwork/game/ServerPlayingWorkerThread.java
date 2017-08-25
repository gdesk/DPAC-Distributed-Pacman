package p2pNetwork.game;

import client.model.MatchImpl;
import client.model.PlayerImpl;
import client.model.character.Character;
import client.model.utils.Point;
import client.model.utils.PointImpl;
import p2pNetwork.utils.ExecutorServiceUtility;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * the goal of this class is to create objects on which
 * it will be possible to invoke remote methods in order to
 * get object current values when it is necessary
 */
public class ServerPlayingWorkerThread implements PeerRegister {

    private static Registry registry;
    private Character character;
    private static ServerPlayingWorkerThread SINGLETON = null;
    private static ServerPlayingWorkerThread objDir = new ServerPlayingWorkerThread();
    private static ServerPlayingWorkerThread objIsAlive = new ServerPlayingWorkerThread();

    private ServerPlayingWorkerThread(){
        this.character = MatchImpl.myCharacter();

    }

    /**
     * here pattern singleton because every peer can have
     * only a single instance of server side
     * @param executor
     * @return
     */
    public static ServerPlayingWorkerThread getIstance(ExecutorServiceUtility executor){
        if(SINGLETON == null){
            SINGLETON = new ServerPlayingWorkerThread();
        }
        return SINGLETON;

    }

    /**
     * this method will always return an updated position for this peer
     * @return
     */
    public Point<Integer, Integer> getPosition() {
        return (PointImpl)(character.position());
    }

    /**
     * this method will always return the current state for this peer (aka character):
     * - true if character is alive
     * - false if character is dead
     * @return
     */
    public Boolean isAlive() {
        return character.isAlive();
    }

    /**
     * this method creates an rmiregistry (default port 1099)
     * where to export objects for remote method invocation.
     * Exported objects are two:
     *  - the former is for the character's direction
     *  - the latter is for the character's state (dead or alive)
     */
    public void run() {

        try {
            registry = LocateRegistry.createRegistry(1099);
            System.setProperty("Djava.rmi.server.codebase", "out/");
            System.setProperty("Djava.rmi.server.hostname", PlayerImpl.ip());

            PeerRegister stubDirection = (PeerRegister) UnicastRemoteObject.exportObject(objDir, 1099);
            registry.bind("direction", stubDirection);

            PeerRegister stubIsAlive = (PeerRegister) UnicastRemoteObject.exportObject(objIsAlive, 1099);
            registry.bind("isAlive", stubIsAlive);

            System.out.println("Server ready");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
