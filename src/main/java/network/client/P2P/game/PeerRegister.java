package network.client.P2P.game;


import client.model.utils.Lives;
import client.model.utils.Point;

import java.rmi.Remote;


public interface PeerRegister extends Remote {

    Point<Object, Object> getPosition();

    int getScore();

    int getLives();

    Boolean isAlive();


}
