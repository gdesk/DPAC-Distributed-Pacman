package network.client.P2P.game;


import client.model.utils.Point;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PeerRegister extends Remote {

    Point<Integer,Integer> getDirection() throws RemoteException;

    Boolean isAlive() throws RemoteException;

}
