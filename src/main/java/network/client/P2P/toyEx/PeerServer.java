package network.client.P2P.toyEx;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Federica on 18/07/17.
 */
public class PeerServer implements Hello {

    private static Boolean isServer;
    private  static Boolean isClient;

    public PeerServer(){
        this.isServer = true;
        this.isClient = false;
    }

    public String sayHello() {
        return "Hello, world!";

    }


    public static void main(String[] args) throws RemoteException {
        PeerServer obj = new PeerServer();
        if(isServer){
            Registry registry = LocateRegistry.createRegistry(1099);
            System.setProperty("Djava.rmi.server.codebase", "out/");
            System.setProperty("Djava.rmi.server.hostname", "192.168.1.250");

            try {


                Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 1099);

                // Bind the remote object's stub in the registry
                //registry = LocateRegistry.getRegistry();
                registry.bind("Hello", stub);

                System.err.println("Server ready");
            } catch (Exception e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }

        }else if(isClient){
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



        }
    }
}
