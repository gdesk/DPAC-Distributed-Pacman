package network.client.P2P.utils;

import network.client.P2P.game.ClientPlayingWorkerThread;
import network.client.P2P.game.ServerPlayingWorkerThread;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Federica on 28/07/17.
 */

public class ExecutorServiceUtility {

    private int poolSize;
    private ExecutorService executor;
    private Runnable worker;
    private Future<?> future;


    public ExecutorServiceUtility(){

        this.poolSize = Runtime.getRuntime().availableProcessors()+1;
        this.executor = Executors.newFixedThreadPool(poolSize);
        this.worker = null;
        this.future = null;
    }


    public void initServerPlayingWorkerThread(){
        this.worker = new ServerPlayingWorkerThread(this);
        executor.execute(worker);
    }

    public void initClientPlayingWorkerThread(){
        try {
            //this.worker = new ClientPlayingWorkerThread(this);
            this.future = executor.submit(new ClientPlayingWorkerThread(this));
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        executor.execute(worker);
    }

    public void stopServerPlayingWorkerThread(){
        executor.shutdown();
        while (!executor.isTerminated()) {}
        System.out.println("All threads (server + clients have been shutdown");
    }

    public void stopClientPlayingWorkerThread(){
        this.future.cancel(true);

    }




}
