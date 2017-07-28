package network.client.P2P.game;

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
    Point<Object, Object> positionResponse;

    public ClientPlayingWorkerThread(){
        this.registry = ClientWorkerThread.getRegistry();
    }


    @Override
    public void run() {

        PeerStateRegister stub;
        Point<Object, Object> positionResponse;
        int scoreResponse;
        int livesResponse;
        boolean isDeadResponse;

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
                stub = (PeerStateRegister) registry.lookup("currentPosition");
                positionResponse = stub.getPosition();

                stub = (PeerStateRegister) registry.lookup("currentScore");
                scoreResponse = stub.getScore();

                stub = (PeerStateRegister) registry.lookup("currentLives");
                livesResponse = stub.getLives();

                stub = (PeerStateRegister) registry.lookup("isDead");
                isDeadResponse = stub.isAlive();


                //TODO COMPATTARE LO STREAM DI DATI IN INGRESSO CON RXJAVA
                wait(1000);

            } catch (RemoteException | NotBoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
