package network.client.P2P.game;

import client.model.utils.Lives;
import client.model.utils.Point;
import network.client.P2P.bootstrap.ClientWorkerThread;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 * Created by Federica on 27/07/17.
 */
public class ClientPlayingWorkerThread implements Runnable {

    private Registry registry;

    public ClientPlayingWorkerThread(){
        this.registry = ClientWorkerThread.getRegistry();
    }


    @Override
    public void run() {

        PeerRegister stub;
        Point<Object, Object> positionResponse;
        int scoreResponse;
        Lives livesResponse;
        boolean isAliveResponse;

        /**
         * ogni client preleva nel registro del server su
         * cui è in ascolto i valori (posizione, punteggio, vite, stato)
         * riguardanti l'altro peer
         *
         * NB. verranno avviati tanti thread di tipo ClientPlayingWorkerThread
         * quanti sono gli ip contenuti nella lista che mi viene mandata dal
         * server di manu (=> peer tot. - 1)
         */
        while(!Thread.currentThread().isInterrupted()) {
            try {
                stub = (PeerRegister) registry.lookup("currentPosition");
                positionResponse = stub.getPosition();

                stub = (PeerRegister) registry.lookup("currentScore");
                scoreResponse = stub.getScore();

                stub = (PeerRegister) registry.lookup("currentLives");
                livesResponse = stub.getLives();

                stub = (PeerRegister) registry.lookup("isAlive");
                isAliveResponse = stub.isAlive();


                //TODO COMPATTARE LO STREAM DI DATI IN INGRESSO SU QUESTO CLIENT
                //TODO E, A SUA VOLTA COMPATTARE CON STREAM DEGLI ALTRI CLIENT
                //TODO SULLO STESSO PEER CON RXJAVA
                wait(1000);

            } catch (RemoteException | NotBoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
