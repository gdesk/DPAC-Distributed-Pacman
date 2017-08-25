package network.client.P2P.utils;

import network.client.P2P.game.ClientPlayingWorkerThread;
import network.client.P2P.game.ServerPlayingWorkerThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * a class to handle efficiently tasks related to peer
 * configuration
 */
public class ExecutorServiceUtility {

    private int poolSize;
    private ExecutorService executor;
    private static Runnable workerClient;
    private static ExecutorServiceUtility SINGLETON = null;

    private ExecutorServiceUtility(){
        this.poolSize = Runtime.getRuntime().availableProcessors()+1;
        this.executor = Executors.newFixedThreadPool(poolSize);

    }

    /**
     * singleton
     * @return
     */
    public static ExecutorServiceUtility getIstance(){
        if(SINGLETON == null){
            SINGLETON = new ExecutorServiceUtility();
        }
        return SINGLETON;
    }

    /**
     * this method starts server configuration
     */
    public void initServerPlayingWorkerThread(){
        ServerPlayingWorkerThread.getIstance(this).run();

    }

    /**
     * this method starts clients configuration.
     * Nb. it will be called as many times as peers are (minus one)
     * @param ip
     */
    public void initClientPlayingWorkerThread(String ip){
        workerClient = new ClientPlayingWorkerThread(this, ip);
        executor.execute(workerClient);

    }

    /**
     * this method stops all clients threads
     */
    public void stopPlayingWorkerThread(){
        executor.shutdown();
        while (!executor.isTerminated()) {}
    }



}
