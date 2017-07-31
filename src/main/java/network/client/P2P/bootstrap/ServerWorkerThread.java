package network.client.P2P.bootstrap;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Created by Federica on 21/07/17.
 */
public class ServerWorkerThread implements Runnable{

    private String ip;
    private int rmiPort;
    private static Registry registry;

    public  ServerWorkerThread() throws UnknownHostException {
        this.ip = InetAddress.getLocalHost().toString();
        registry = null;
        configureRmiPort();

    }



    @Override
    public void run() {

        try {
            registry = LocateRegistry.createRegistry(rmiPort);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.setProperty("Djava.rmi.server.codebase", "out/");
        System.setProperty("Djava.rmi.server.hostname", ip);

        System.out.println("Rmiregistry configured");

    }

    /**
     * this method configures server port on which
     * rmiregisty has to be launched
     */
    private void configureRmiPort() {
        int count = 0;
        int maxTries = 5;
        while (true) {
            try {
                this.rmiPort = PortRangeHandler.getPortNumber();
                break;
            } catch (Exception ex) {
                this.rmiPort = PortRangeHandler.getNextPortNumber();
                if (++count == maxTries) {
                    throw ex;
                }
            }
        }

    }

    public static Registry getRegistry(){
        return registry;
    }


}
