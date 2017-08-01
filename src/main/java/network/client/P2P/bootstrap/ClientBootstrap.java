package network.client.P2P.bootstrap;


import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Federica on 21/07/17.
 */
public class ClientBootstrap {

    //private String ip;
    private static Registry registry;

    public ClientBootstrap(String ip) throws UnknownHostException {
        //this.ip = ip;
        System.setProperty("Djava.rmi.server.hostname", ip);
        try {
            registry = LocateRegistry.getRegistry(ip);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static Registry getRegistry(){
        return registry;
    }
}
