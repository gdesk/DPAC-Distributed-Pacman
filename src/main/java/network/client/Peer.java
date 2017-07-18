package network.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by Federica on 18/07/17.
 */
public class Peer implements Hello{

    public String id;

    //a set containg ips of other peers
    public static Set<String> clientIps;

    public Peer(Set<String> clientIps) throws UnknownHostException {
        this.id = InetAddress.getLocalHost().toString();
        this.clientIps = clientIps;
    }


    @Override
    public String sayHello() throws RemoteException {
        return "[" + id + "] Hello, world!";
    }


    public static void main(String[] args) {
        //start server

        new Thread(() -> {

        });

        /**
         * start client
         *
         * creo un thread per ogni peer con cui questo peer
         * dovrà comunicare (=> n peer tot. - 1)
         */
        for(String ip: clientIps) {

            new Thread(() -> {

            });

        }

        //gestione concorrenza
        //while(){ //game is over

        //}

    }


    @Override
    public String toString() {
        return "Peer{" +
                "id='" + id + '\'' +
                '}';
    }
}
