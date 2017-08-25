package p2pNetwork.game;


import client.controller.BaseControllerCharacter;
import client.model.Direction;
import client.model.MatchImpl;
import client.model.utils.Point;
import client.model.utils.PointImpl;
import javafx.util.Pair;
import p2pNetwork.utils.ExecutorServiceUtility;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Observable;

/**
 * the goal of this class is to update values, on this peer, concerning
 * other peers.
 * NB. the number of instances created for this class type depends on the
 * totale number of peers enjoing the match minus one
 */
public class ClientPlayingWorkerThread extends Observable implements Runnable {

    private ExecutorServiceUtility executor;
    private String ip;

    private Point<Integer, Integer> prepos;

    public ClientPlayingWorkerThread
            (ExecutorServiceUtility executor, String ip) {
        this.executor = executor;
        this.ip = ip;

    }

    /**
     * this method, once recovered reference to its server, looks
     * for objects previously binded in registry and then invokes
     * remote methods on them in order to get their current values
     */
    @Override
    public void run() {

        System.setProperty("Djava.rmi.server.hostname", ip);
        String host = ip;
        setPrepos((PointImpl)MatchImpl.character(ip).get().position());

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

                if(!getPrepos().equals(pos)) {

                        if (!getPrepos().x().equals(pos.x())) {
                            if(getPrepos().x() < pos.x()){

                                new Thread(() -> {

                                    BaseControllerCharacter.update(this, new Pair<>(ip, new Pair<>(pos,Direction.RIGHT)));
                                }).start();

                            }else{

                                new Thread(() -> {

                                    BaseControllerCharacter.update(this, new Pair<>(ip, new Pair<>(pos,Direction.LEFT)));
                                }).start();
                            }
                        }else{

                            if(getPrepos().y() < pos.y()){

                                new Thread(() -> {
                                    BaseControllerCharacter.update(this, new Pair<>(ip, new Pair<>(pos,Direction.UP)));
                                }).start();

                            }else{

                                new Thread(() -> {
                                    BaseControllerCharacter.update(this, new Pair<>(ip, new Pair<>(pos,Direction.DOWN)));
                                }).start();

                            }
                        }

                        setPrepos(pos);

                }

                boolean isAlive = stubisAlive.isAlive();

                if(!isAlive) {
                    BaseControllerCharacter.update(this, new Pair<>(ip, isAlive));
                    executor.stopPlayingWorkerThread();
                }
            } catch (Exception e) {
                //System.err.println("Client " + ip + " exception: " + e.toString());
                //e.printStackTrace();
            }

        }

    }

    /**
     * this method is necessary to correctly update position in peer view
     * @param pos
     */
    private synchronized void setPrepos(final Point<Integer,Integer> pos){
        prepos = pos;
    }

    /**
     * this method gets the previous peer position
     * @return
     */
    private synchronized Point<Integer,Integer> getPrepos(){
        return prepos;
    }
}