package network.client.P2P.game;


import client.model.Direction;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PeerRegister extends Remote {

    Direction getDirection() throws RemoteException;

    Boolean isAlive() throws RemoteException;

}
