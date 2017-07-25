package network.client.p2pCommunication; /**
 * Created by Federica on 16/07/17.
 */


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PeerStateRegister extends Remote {

    String sayHello() throws RemoteException;

    String getPosition();

    int getScore();

    Boolean isAlive();


}
