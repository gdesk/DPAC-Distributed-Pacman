package network.client.P2P.bootstrap;

import akka.actor.ActorSystem;
import akka.actor.Inbox;
import network.client.P2P.messages.PeerMessages;
import org.json.JSONObject;
import scala.concurrent.duration.FiniteDuration;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Federica on 21/07/17.
 */
public class ClientWorkerThread implements Runnable {

    private String ip;

    /**
     * a set containg ips of other peers.
     * these ips are useful when I need to peer
     * act like client and need to know the server ip
     */
    private Set<String> serverIps;
    private final Inbox inbox;
    private final JSONObject message;


    public ClientWorkerThread(String ip) throws UnknownHostException {
        this.ip = ip;
        this.inbox = Inbox.create(system);
        this.message   = new JSONObject();

    }

    @Override
    public void run() {

        if(startClients()) {
            System.setProperty("Djava.rmi.server.hostname", ip);
            String host = ip;

            try {
                Registry registry = null;
                try {
                    registry = LocateRegistry.getRegistry(host);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

                PeerStateRegister stub = (PeerStateRegister) registry.lookup("Hello from M");
                //currentWorking.PeerStateRegister stub = (currentWorking.PeerStateRegister) registry.lookup("Hello from F");
                String response = stub.sayHello();
                System.out.println("response: " + response);


            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

    }



    /**
     * this method checks if client threads can
     * start running on this peer. Before do that,
     * they have to wait until every peer server
     * has been properly configured.
     * @return
     */
    private boolean startClients() {
        try {
            if(this.inbox.toString().equals(PeerMessages.CLIENTS_CAN_START_RUNNING)){
                this.inbox.receive(FiniteDuration.apply(10, TimeUnit.SECONDS));
                return true;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return false;
    }
}
