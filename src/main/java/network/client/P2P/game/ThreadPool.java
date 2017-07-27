package network.client.P2P.game;

import network.client.P2P.bootstrap.ClientWorkerThread;
import network.client.P2P.bootstrap.ServerWorkerThread;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Federica on 21/07/17.
 */
//TODO DA SISTEMARE
public class ThreadPool {


    public static void main(String[] args) throws UnknownHostException, InterruptedException {

        Boolean isMyPeerAlive = true;

        Set<String> serverIps = new HashSet<>(Arrays.asList("192.168.1.6"));
        //Set<String> serverIps = new HashSet<>(Arrays.asList("192.168.1.2"));
        // "Loris' ip"));

        int poolSize = Runtime.getRuntime().availableProcessors()+1;

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        Runnable worker;

        //start server
        worker = new ServerWorkerThread();
        executor.execute(worker);


        /**
         * start clients
         *
         * creo un thread per ogni peer con cui questo peer
         * dovrà comunicare (=> n peer tot. - 1)
         */
        for(String ip: serverIps) {
            worker = new ClientWorkerThread(ip);
            executor.execute(worker);

        }

        /**
         * problema gestion concorrenza:
         * - non in lettura ma in SCRITTURA
         * -
         */
        //while(){ //game is over

        //}

        while(isMyPeerAlive){
            Thread.sleep(10000);
            isMyPeerAlive = false;
        }

        executor.shutdown();
        while(!executor.isTerminated()){}
        System.out.println("Finished all threads");



    }




}
