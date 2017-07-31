package network.client.P2P.bootstrap;

import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Federica on 27/07/17.
 */
public class PeerBootstrap {

    private static Set<String> serverIps;
    private int poolSize;
    private ExecutorService executor;
    private Runnable worker;

    public PeerBootstrap() {


        this.poolSize = Runtime.getRuntime().availableProcessors()+1;
        this.executor = Executors.newFixedThreadPool(poolSize);
        try {
            initServerThread();
            initClientThreads();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }

    /**
     * this method starts server for this peer
     */
    private void initServerThread() throws UnknownHostException {
        this.worker = new ServerWorkerThread();
        executor.execute(worker);

    }

    /**
     * this method starts clients for this peer
     *
     * creo un thread per ogni peer con cui questo peer
     * dovrà comunicare (=> n peer tot. - 1)
     */
    private void initClientThreads() throws UnknownHostException {
        for(String ip: serverIps) {
            worker = new ClientWorkerThread(ip);
            executor.execute(worker);

        }

    }

    public static Set<String> getserverIps(){
        return serverIps;
    }
}
