package network.client.P2P.game;


import client.controller.BaseControllerCharacter;
import client.model.Direction;
import javafx.util.Pair;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Federica on 27/07/17.
 *
 * this class is useful to retrieve from other peers,
 * character info I need to update view on this peer
 *
 */
public class ClientPlayingWorkerThread extends Observable implements Runnable {

    //private ExecutorServiceUtility executor;
    private String ip;
    private Registry registry;
    //private Map<String, Object> responses;
    //private ObservableCharacter observableCharacter;
    private List<Object> list;
    //private Character character;
    //private ClientIncomingMessageHandlerImpl characterHandler;

    public ClientPlayingWorkerThread
            (ExecutorServiceUtility executor, String ip) {

        //this.executor = executor;
        this.ip = ip;
        this.registry = registry;
        //this.observableCharacter = new ObservableCharacter();
        this.list = new LinkedList<>();
        //this.character = MatchImpl.myCharacter();
        //this.characterHandler = new ClientIncomingMessageHandlerImpl();
    }


    @Override
    public void run() {

        System.setProperty("Djava.rmi.server.hostname", ip);
        String host = ip;
        Direction prevDir = Direction.START;
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(host);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        while (!Thread.currentThread().isInterrupted()) {

            try {

                PeerRegister stubDirection = (PeerRegister) registry.lookup("direction");
                PeerRegister stubisAlive = (PeerRegister) registry.lookup("isAlive");

                Direction direction = stubDirection.getDirection();

                if(!prevDir.equals(direction)) {
                    BaseControllerCharacter.update(this, new Pair<>(ip, direction));
                    prevDir = direction;
                }

                boolean isAlive = stubisAlive.isAlive();

                if(!isAlive) {
                    BaseControllerCharacter.update(this, new Pair<>(ip, isAlive));
                }

            } catch (Exception e) {
                //System.err.println("Client " + ip + " exception: " + e.toString());
                //e.printStackTrace();
            }


        }

    }
}