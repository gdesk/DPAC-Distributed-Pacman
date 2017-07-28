package network.client.P2P.bootstrap;

import network.client.P2P.game.PeerStateRegister;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Created by Federica on 21/07/17.
 */
public class ServerWorkerThread implements PeerStateRegister, Runnable{

    private String ip;
    private int rmiPort;
    private JSONObject message;
    private static Registry registry;

    public  ServerWorkerThread() throws UnknownHostException {
        this.ip = InetAddress.getLocalHost().toString();
        registry = null;
        configureRmiPort();
        configureServerSystemCommunication();

    }

    @Override
    public String sayHello() throws RemoteException {
        return "[" + ip + "] Hello, world!";
    }

    /*@Override
    public String getPosition() {
        return null;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public Boolean isAlive() {
        return null;
    }*/

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

    /**
     * this method sets up communication between this class (currentWorking.ServerWorkerThread)
     * and a server actor (MessageReceiverActor). So that it is possible to handle
     * interaction between object oriented and Actor paradigms.
     */
    private void configureServerSystemCommunication(){

        //TODO
        /*this.system = ActorSystem.create("PeerServerSystem");
        this.inbox = Inbox.create(system);
        this.message   = new JSONObject();*/

    }

    public static Registry getRegistry(){
        return registry;
    }


}
