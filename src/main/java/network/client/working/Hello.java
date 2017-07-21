package network.client.working; /**
 * Created by Federica on 16/07/17.
 */


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Hello extends Remote {

    String sayHello() throws RemoteException;

}
