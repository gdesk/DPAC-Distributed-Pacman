package network.client.P2P.game;


import client.model.utils.Point;

import java.rmi.Remote;


public interface PeerRegister extends Remote {

    Point<Object, Object> getPosition();

    int getScore();

    String getLives();

    Boolean isAlive();


}
