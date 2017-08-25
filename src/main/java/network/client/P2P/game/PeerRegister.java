package network.client.P2P.game;

import client.model.utils.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * this interface contains method that can be invoked
 * on remote objects exported on rmi registry
 */
public interface PeerRegister extends Remote {

    /**
     * this methods returns the position of character (aka peer)
     * @return
     * @throws RemoteException
     */
    Point<Integer,Integer> getPosition() throws RemoteException;

    /**
     * this methods return the state of character (aka peer)
     * Nb. state is true if character is alive, false if dead
     * @return
     * @throws RemoteException
     */
    Boolean isAlive() throws RemoteException;

}
