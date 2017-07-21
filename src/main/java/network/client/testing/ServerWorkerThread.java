package network.client.testing;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Federica on 21/07/17.
 */
public class ServerWorkerThread implements PeerStateRegister, Runnable{

    private int rmiPort;
    private String ip;

    public  ServerWorkerThread() throws UnknownHostException {
        this.ip = InetAddress.getLocalHost().toString();
        //------SOLO PER PROVA
        this.rmiPort = 1099; //------DA CAMBIARE PER OGNI MACCHINA FISICA
    }

    @Override
    public String sayHello() throws RemoteException {
        return "[" + ip + "] Hello, world!";
    }

    @Override
    public void run() {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(rmiPort);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.setProperty("Djava.rmi.server.codebase", "out/");
        System.setProperty("Djava.rmi.server.hostname", ip);

        try {

            ServerWorkerThread obj = new ServerWorkerThread();
            PeerStateRegister stub = (PeerStateRegister) UnicastRemoteObject.exportObject(obj, rmiPort);

            // Bind the remote object's stub in the registry
            //registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);


            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void setRmiPort(int port){
        this.rmiPort = port;
    }

}
