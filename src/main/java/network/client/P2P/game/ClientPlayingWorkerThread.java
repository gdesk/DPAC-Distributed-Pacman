package network.client.P2P.game;


import client.model.Direction;
import client.model.MatchImpl;
import client.model.character.Character;
import network.client.P2P.utils.ExecutorServiceUtility;
import network.client.rxJava.ObservableCharacter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Federica on 27/07/17.
 *
 * this class is useful to retrieve from other peers,
 * character info I need to update view on this peer
 *
 */
public class ClientPlayingWorkerThread implements Runnable {

    private ExecutorServiceUtility executor;
    private String ip;
    private Registry registry;
    private Map<String, Object> responses;
    private ObservableCharacter observableCharacter;
    private List<Object> list;
    private Character character;

    public ClientPlayingWorkerThread
            (ExecutorServiceUtility executor, String ip, Registry registry) {

        this.executor = executor;
        this.ip = ip;
        this.registry = registry;

        //initialize client character
        this.responses = new HashMap<String, Object>() {{
            put("direction", Direction.START);
            put("isAlive", false);

        }};
        this.observableCharacter = new ObservableCharacter();
        this.list = new LinkedList<>();
        this.character = MatchImpl.myCharacter();
    }


    @Override
    public void run() {

        /*System.out.println("ClientPlayingWorkerThread");
        PeerRegister stub;
        Object response;
        List<Object> tris = new LinkedList<>();

        while (!Thread.currentThread().isInterrupted()) {

            try {
                for (Map.Entry<String, Object> pair : responses.entrySet()) {

                    stub = (PeerRegisterImlp) registry.lookup(pair.getKey());
                    switch (pair.getKey()) {
                        case "direction":
                            response = stub.getDirection();
                            break;

                        case "isAlive":
                            response = stub.isAlive().toString();
                            //https://www.google.it/search?site=&source=hp&q=EXAMPLE+With+runnable+cancel&oq=EXAMPLE+With+runnable+cancel&gs_l=psy-ab.3..33i21k1l2.2744.8760.0.8989.31.29.1.0.0.0.119.2657.20j8.28.0....0...1.1.64.psy-ab..2.29.2661.0..0j0j35i39k1j0i67k1j0i131k1j0i22i30k1j0i19k1j0i22i30i19k1j0i13i30k1j0i13i5i30k1j33i22i29i30k1j33i160k1.VRQ-mzgOFBY
                            executor.stopClientPlayingWorkerThread();
                            break;

                        default:
                            response = new Object();

                    }

                    if (!response.equals(pair.getValue())) {
                        pair.setValue(response);
                        tris.add(ip);
                        tris.add(pair.getKey());
                        tris.add(response);

                        /**
                         * aggiorno lo stato degli ALTRI personaggi nella MIA view
                         * STREAM -> ip, "move", currentPosition);
                         * this class is OBSERVABLE because creates a stream
                         */
                      /*  observableCharacter.subscribeObserver(tris);
                    }

                    tris.clear();

                }

                //wait(1000);

            } catch(RemoteException | NotBoundException | InterruptedException e){
                e.printStackTrace();

            }*/
                      

        System.setProperty("Djava.rmi.server.hostname", ip);
        String host = ip;

        while (!Thread.currentThread().isInterrupted()) {

            try {

                Registry registry = LocateRegistry.getRegistry(host);
                PeerRegister stubDirection = (PeerRegister) registry.lookup("direction");
                PeerRegister stubisAlive = (PeerRegister) registry.lookup("isAlive");

                Direction direction = stubDirection.getDirection();

                list.add(ip);
                list.add("direction");
                list.add(direction);
                observableCharacter.subscribeObserver(list);

                System.out.println(list);

                list.clear();

                boolean isAlive = stubisAlive.isAlive();

                list.add(ip);
                list.add("isAlive");
                list.add(isAlive);
                observableCharacter.subscribeObserver(list);
                list.clear();

            } catch (Exception e) {
                //System.err.println("Client " + ip + " exception: " + e.toString());
                //e.printStackTrace();
            }




        }

    }
}