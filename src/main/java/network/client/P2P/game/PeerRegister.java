package network.client.P2P.game;


import client.model.Direction;

import java.rmi.Remote;


public interface PeerRegister extends Remote {

    //Point<Object, Object> getPosition();

    Direction getDirection();

    Boolean isAlive();

}
