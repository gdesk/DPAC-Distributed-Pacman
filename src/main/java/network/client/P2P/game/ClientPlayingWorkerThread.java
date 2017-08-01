package network.client.P2P.game;

import client.model.peerCommunication.ClientIncomingMessageHandler;
import client.model.peerCommunication.ClientIncomingMessageHandlerImpl;
import network.client.P2P.bootstrap.ClientBootstrap;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Federica on 27/07/17.
 */
public class ClientPlayingWorkerThread implements Runnable {

    private Registry registry;
    private ClientIncomingMessageHandler handler;
    private Map<String, Object> responses;
    private ExecutorServiceUtility executor;

    public ClientPlayingWorkerThread(ExecutorServiceUtility executor) throws RemoteException, NotBoundException {
        this.registry = ClientBootstrap.getRegistry();
        this.handler = new ClientIncomingMessageHandlerImpl();
        this.executor = executor;

        //initialize client character
        this.responses = new HashMap<String, Object>() {{
            //TODO put("currentPosition", Point<Object, Object>);
            put("currentScore", "");
            put("currentLives", "");
            put("isAlive", "");

        }};

    }

    @Override
    public void run() {
        PeerRegister stub;
        Object response;
        while (!Thread.currentThread().isInterrupted()) {

            try {
                for (Map.Entry<String, Object> pair : responses.entrySet()) {

                    switch (pair.getKey()) {
                        case "currentPosition":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            //TODO response = new Point(stub.getPosition().x(), stub.getPosition().y().toString());
                            break;

                        case "currentScore":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = String.valueOf(stub.getScore());
                            break;

                        case "currentLives":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = stub.getLives();
                            break;

                        case "isAlive":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = stub.isAlive().toString();

                            //https://www.google.it/search?site=&source=hp&q=EXAMPLE+With+runnable+cancel&oq=EXAMPLE+With+runnable+cancel&gs_l=psy-ab.3..33i21k1l2.2744.8760.0.8989.31.29.1.0.0.0.119.2657.20j8.28.0....0...1.1.64.psy-ab..2.29.2661.0..0j0j35i39k1j0i67k1j0i131k1j0i22i30k1j0i19k1j0i22i30i19k1j0i13i30k1j0i13i5i30k1j33i22i29i30k1j33i160k1.VRQ-mzgOFBY
                            executor.stopClientPlayingWorkerThread();
                            break;

                        default:
                            response = new Object();

                    }

                    if (!response.equals(pair.getValue())) {
                        pair.setValue(response);
                        /**
                         * TODO
                         *
                         * - per il momento chiamo questo metodo:
                         * this.handler.update(character);
                         *
                         * PROBLEMA:
                         * - il character che sto passando NON è quello del
                         * mio peer, ma relativo ad un alro peer.
                         * Infatti, ad esempio, il character che passo,
                         * dovrebbe contenere le info relative (es. allo spostamento)
                         * ad un altro character (che sta un altro peer) =>
                         * non posso passare questo riferimento.
                         *
                         * SOLUZIONE:
                         * - chiamo qui un metodo update che prende in ingresso 2 paramentri:
                         *               -stringa relativa al valore da aggiornare  es. "position"
                         *               -nuovo valore es. "(0,1)"
                         *
                         * - questo metodo richiamerà il controller di questo peer
                         * - che a sua volta aggiornera la view di questo peer
                         * con le nuove posizioni degli ALTRI peer
                         *
                         * this.handler.update(pair.getKey(), character); */


                    }


                    wait(1000);

                }
            } catch(RemoteException | NotBoundException | InterruptedException e){
                e.printStackTrace();

            }
        }
    }
}