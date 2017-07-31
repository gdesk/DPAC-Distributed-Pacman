package network.client.P2P.bootstrap;


import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Federica on 21/07/17.
 */
public class ClientWorkerThread implements Runnable {

    private String ip;
    private static Registry registry;

    public ClientWorkerThread(String ip) throws UnknownHostException {
        this.ip = ip;
        registry = null;

    }

    @Override
    public void run() {

        System.setProperty("Djava.rmi.server.hostname", ip);
        String host = ip;
        try {
            registry = LocateRegistry.getRegistry(host);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public static Registry getRegistry(){
        return registry;
    }
}
