package network.client.P2P.game;

import client.model.peerCommunication.ClientIncomingMessageHandler;
import client.model.peerCommunication.ClientIncomingMessageHandlerImpl;
import io.reactivex.Observable;
import network.client.P2P.bootstrap.ClientWorkerThread;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Federica on 27/07/17.
 */
public class ClientPlayingWorkerThread implements Callable<List<String>> {

    private Registry registry;
    private ClientIncomingMessageHandler handler;
    private List<String> list;

    public ClientPlayingWorkerThread() {
        this.registry = ClientWorkerThread.getRegistry();
        this.handler = new ClientIncomingMessageHandlerImpl();
        this.list = new LinkedList<>();
    }


    @Override
    public List<String> call() throws Exception {

        PeerRegister stub;
        String positionResponseX;
        String positionResponseY;
        String scoreResponse;
        String livesResponse;
        String isAliveResponse;

        /**
         * ogni client preleva nel registro del server su
         * cui è in ascolto i valori (posizione, punteggio, vite, stato)
         * riguardanti l'altro peer
         *
         * NB. verranno avviati tanti thread di tipo ClientPlayingWorkerThread
         * quanti sono gli ip contenuti nella lista che mi viene mandata dal
         * server di manu (=> peer tot. - 1)
         */
        while (!Thread.currentThread().isInterrupted()) {
            Observable<String> observable;
            try {
                stub = (PeerRegister) registry.lookup("currentPosition");
                positionResponseX = stub.getPosition().x().toString();
                positionResponseY = stub.getPosition().y().toString();
                this.list.add(positionResponseX);
                this.list.add(positionResponseY);

                stub = (PeerRegister) registry.lookup("currentScore");
                scoreResponse = String.valueOf(stub.getScore());
                this.list.add(scoreResponse);

                stub = (PeerRegister) registry.lookup("currentLives");
                livesResponse = stub.getLives();
                this.list.add(livesResponse);

                stub = (PeerRegister) registry.lookup("isAlive");
                isAliveResponse = stub.isAlive().toString();
                this.list.add(isAliveResponse);

                wait(1000);

                return list;

            } catch (RemoteException | NotBoundException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }
}