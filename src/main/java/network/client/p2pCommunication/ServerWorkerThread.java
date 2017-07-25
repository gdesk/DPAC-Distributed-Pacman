package network.client.p2pCommunication;

import akka.actor.ActorSystem;
import akka.actor.Inbox;
import org.json.JSONObject;

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

    private String ip;
    private int rmiPort;
    private ActorSystem system;
    private Inbox inbox;
    private JSONObject message;
    //TODO SCOMMENTARE PRIMA DI PUSHARE SU DEVELOP
    //private ActorRef bootstrapManager;

    public  ServerWorkerThread() throws UnknownHostException {
        this.ip = InetAddress.getLocalHost().toString();
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
            registry.bind("Hello from F", stub);
            //registry.bind("Hello from M", stub);
            System.out.println("Server ready");
            this.message.put(ip, PeerMessages.SERVER_IS_RUNNING);
            //TODO SCOMMENTARE PRIMA DI PUSHARE SU DEVELOP
            //this.inbox.send(bootstrapManager, message);
            //TODO GESTIRE CHE L'INVIO DEL MESSAGGIO NON VADA A BUON FINE
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

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
     * this method sets up communication between this class (ServerWorkerThread)
     * and a server actor (MessageReceiverActor). So that it is possible to handle
     * interaction between object oriented and Actor paradigms.
     */
    private void configureServerSystemCommunication(){
        this.system = ActorSystem.create("PeerServerSystem");
        this.inbox = Inbox.create(system);
        this.message   = new JSONObject();
        //TODO SCOMMENTARE PRIMA DI PUSHARE SU DEVELOP
        //this.bootstrapManager = system.actorOf(new Props(MessageReceiverActor.class, "MessageReceiverActor"));

    }


}
