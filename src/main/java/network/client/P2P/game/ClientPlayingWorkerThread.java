package network.client.P2P.game;


import client.model.Direction;
import network.client.P2P.toyEx.Hello;
import network.client.P2P.utils.ExecutorServiceUtility;
import network.client.rxJava.ObservableCharacter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
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
        //String host = (args.length < 1) ? null : args[0];
        String host = ip;

        boolean isrunning = true;

        while(isrunning) {
            try {

                Registry registry = LocateRegistry.getRegistry(host);
                Hello stub = (Hello) registry.lookup("Hello");
                String response = stub.sayHello();

                System.out.println("response: " + response);


                isrunning = false;
                
            } catch (Exception e) {
                //System.err.println("Client exception: " + e.toString());
                System.err.println(e.getClass());
                e.printStackTrace();
            }
        }


    }
}