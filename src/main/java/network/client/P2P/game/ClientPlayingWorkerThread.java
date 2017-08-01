package network.client.P2P.game;

import client.model.peerCommunication.ClientIncomingMessageHandler;
import client.model.peerCommunication.ClientIncomingMessageHandlerImpl;
import network.client.P2P.bootstrap.ClientBootstrap;

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
    private Map<String, String> responses;

    public ClientPlayingWorkerThread() throws RemoteException, NotBoundException {
        this.registry = ClientBootstrap.getRegistry();
        this.handler = new ClientIncomingMessageHandlerImpl();

        //initialize client character
        this.responses = new HashMap<String, String>() {{
            put("currentPositionX", "");
            put("currentPositionY", "");
            put("currentScore", "");
            put("currentLives", "");
            put("currentIsDead", "");

        }};

    }


    @Override
    public void run() {
        PeerRegister stub;
        String response;
        while (!Thread.currentThread().isInterrupted()) {

            try {
                for (Map.Entry<String, String> pair : responses.entrySet()) {

                    switch (pair.getKey()) {
                        case "currentPositionX":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = stub.getPosition().x().toString();
                            break;

                        case "currentPositionY":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = stub.getPosition().y().toString();
                            break;

                        case "currentScore":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = String.valueOf(stub.getScore());
                            break;

                        case "currentLives":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = stub.getLives();
                            break;

                        case "currentIsDead":
                            stub = (PeerRegister) registry.lookup(pair.getKey());
                            response = stub.isAlive().toString();
                            break;

                        default:
                            response = "";

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