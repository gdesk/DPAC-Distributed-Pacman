package network.client.testing;

import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

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


    public ClientWorkerThread(String ip) throws UnknownHostException {
        this.ip = ip;

    }

    @Override
    public void run() {
        System.setProperty("Djava.rmi.server.hostname", ip);
        //String host = (args.length < 1) ? null : args[0];
        String host = ip;
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            PeerStateRegister stub = (PeerStateRegister) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }


    }

    /**
     *
     * @param serverIps
     *
     * clientIps is set from an actor (p.e.
     * OutocominPeerHandlerActor who will receive a
     * json message (p.e. json(actorIp, Set<String> ips))
     * from server)
     */
    public void setServerIps(Set<String> serverIps){
        this.serverIps = serverIps;
    }

}
