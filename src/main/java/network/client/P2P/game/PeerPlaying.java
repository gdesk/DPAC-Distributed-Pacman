package network.client.P2P.game;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Federica on 27/07/17.
 */
public class PeerPlaying {

    private int poolSize;
    private ExecutorService executor;
    private Runnable worker;

    public PeerPlaying() {

        this.poolSize = Runtime.getRuntime().availableProcessors()+1;
        this.executor = Executors.newFixedThreadPool(poolSize);
        try {
            initServerPlayingThread();
            initClientPlayingThreads();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }

    /**
     * this method starts server for this peer
     */
    private void initServerPlayingThread() throws UnknownHostException {
        this.worker = new ServerPlayingWorkerThread();
        executor.execute(worker);

    }

    /**
     * this method starts clients for this peer
     *
     * creo un thread per ogni peer con cui questo peer
     * dovrà comunicare (=> n peer tot. - 1)
     */
    private void initClientPlayingThreads() throws UnknownHostException {
        //for(String ip: serverIps) {
            worker = new ClientPlayingWorkerThread();
            executor.execute(worker);

        //}

    }





}
