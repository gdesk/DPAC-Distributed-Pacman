package network.client.P2P.game;


import client.controller.BaseControllerCharacter;
import client.model.Direction;
import client.model.MatchImpl;
import client.model.utils.Point;
import client.model.utils.PointImpl;
import javafx.util.Pair;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.NotBoundException;
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

    private ExecutorServiceUtility executor;
    private String ip;
    private Registry registry;
    //private Map<String, Object> responses;
    //private ObservableCharacter observableCharacter;
    private List<Object> list;
    private String characterName;
    //private ClientIncomingMessageHandlerImpl characterHandler;

    public ClientPlayingWorkerThread
            (ExecutorServiceUtility executor, String ip) {

        this.executor = executor;
        this.ip = ip;
        this.registry = registry;
        //this.observableCharacter = new ObservableCharacter();
        this.list = new LinkedList<>();
        this.characterName = MatchImpl.myCharacter().name();
        //this.characterHandler = new ClientIncomingMessageHandlerImpl();
    }


    @Override
    public void run() {

        System.setProperty("Djava.rmi.server.hostname", ip);
        String host = ip;
        Point<Integer, Integer> prepos = (PointImpl)MatchImpl.character(ip).get().position();


        Registry registry;
        PeerRegister stubDirection = null;
        PeerRegister stubisAlive = null;
        try {
            registry = LocateRegistry.getRegistry(host);
            stubDirection = (PeerRegister) registry.lookup("direction");
            stubisAlive = (PeerRegister) registry.lookup("isAlive");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

        while(!Thread.currentThread().isInterrupted()) {
            try {
                Point<Integer, Integer> pos = stubDirection.getPosition();

                if(!prepos.equals(pos)) {

                        if (!prepos.x().equals(pos.x())) {
                            if(prepos.x() < pos.x()){
                                new Thread(() -> {
                                    BaseControllerCharacter.update(this, new Pair<>(ip, Direction.RIGHT));
                                }).start();

                            }else{
                                new Thread(() -> {
                                    BaseControllerCharacter.update(this, new Pair<>(ip, Direction.LEFT));
                                }).start();
                            }

                        }else{
                            if(prepos.y() < pos.y()){
                                new Thread(() -> {
                                    BaseControllerCharacter.update(this, new Pair<>(ip, Direction.UP));
                                }).start();

                            }else{
                                new Thread(() -> {
                                    BaseControllerCharacter.update(this, new Pair<>(ip, Direction.DOWN));
                                }).start();

                            }
                        }

                    prepos = pos;
                }

                boolean isAlive = stubisAlive.isAlive();

                if(!isAlive) {
                    BaseControllerCharacter.update(this, new Pair<>(ip, isAlive));
                    //executor.stopPlayingWorkerThread();
                }
            } catch (Exception e) {
                //System.err.println("Client " + ip + " exception: " + e.toString());
                //e.printStackTrace();
            }


        }

    }
}