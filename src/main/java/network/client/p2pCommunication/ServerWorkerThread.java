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
    private final ActorSystem system;
    private final Inbox inbox;
    private final JSONObject message;
    //TODO SCOMMENTARE PRIMA DI PUSHARE SU DEVELOP
    //private final ActorRef bootstrapManager;

    public  ServerWorkerThread() throws UnknownHostException {
        this.ip = InetAddress.getLocalHost().toString();
        this.rmiPort = 1099; //------SOLO PER PROVA, DA CAMBIARE PER OGNI MACCHINA FISICA  //this.rmiPort = 1100;
        this.system = ActorSystem.create("ServerPeerSystem");
        this.inbox = Inbox.create(system);
        this.message   = new JSONObject();
        //TODO SCOMMENTARE PRIMA DI PUSHARE SU DEVELOP
        // this.bootstrapManager = system.actorOf(new Props(MessageReceiverActor.class, "MessageReceiverActor"));
    }

    @Override
    public String sayHello() throws RemoteException {
        return "[" + ip + "] Hello, world!";
    }

    @Override
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

            registry.bind("Hello from F", stub);
            //registry.bind("Hello from M", stub);

            System.err.println("Server ready");
            this.message.put(ip, PeerMessages.SERVER_IS_RUNNING);
            //TODO SCOMMENTARE PRIMA DI PUSHARE SU DEVELOP
            //this.inbox.send(bootstrapManager, message);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }

    public void setRmiPort(int port){
        this.rmiPort = port;
    }


}
