package network.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

/**
 * Created by Federica on 18/07/17.
 */
public class Peer implements Hello{

    public String id;
    public static int rmiPort;

    //a set containg ips of other peers
    public static Set<String> clientIps;


    public Peer() throws UnknownHostException {
        this.id = InetAddress.getLocalHost().toString();
        this.rmiPort = PortRangeGenerator.getAvailablePortNumber();
    }


    @Override
    public String sayHello() throws RemoteException {
        return "[" + id + "] Hello, world!";
    }


    public static void main(String[] args) {
        //start server
        /*Peer obj = null;
        try {
            obj = new Peer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/
        new Thread(() -> {
            Registry registry = null;
            try {
                registry = LocateRegistry.createRegistry(rmiPort);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.setProperty("Djava.rmi.server.codebase", "out/");
            System.setProperty("Djava.rmi.server.hostname", "192.168.1.250");

            try {

                Peer obj = new Peer();
                Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, rmiPort);

                // Bind the remote object's stub in the registry
                //registry = LocateRegistry.getRegistry();
                registry.bind("Hello", stub);

                System.err.println("Server ready");
            } catch (Exception e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }
        });

        /**
         * start client
         *
         * creo un thread per ogni peer con cui questo peer
         * dovrà comunicare (=> n peer tot. - 1)
         */
        for(String ip: clientIps) {

            new Thread(() -> {
                System.setProperty("Djava.rmi.server.hostname", "192.168.1.250");
                //String host = (args.length < 1) ? null : args[0];
                String host = "192.168.1.250";
                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    Hello stub = (Hello) registry.lookup("Hello");
                    String response = stub.sayHello();
                    System.out.println("response: " + response);
                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            });

        }

        /**
         * problema gestion concorrenza:
         * - non in lettura ma in SCRITTURA
         * -
         */
        //while(){ //game is over

        //}

    }


    /**
     *
     * @param clientIps
     *
     * clientIps is set from an actor (p.e.
     * OutocominPeerHandlerActor who will receive a
     * json message (p.e. json(actorIp, Set<String> ips))
     * from server)
     */
    public void setclientIps(Set<String> clientIps){
        this.clientIps = clientIps;
    }

    @Override
    public String toString() {
        return "Peer{" +
                "id='" + id + '\'' +
                '}';
    }


}
