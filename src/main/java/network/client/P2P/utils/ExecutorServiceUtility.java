package network.client.P2P.utils;

import network.client.P2P.game.ClientPlayingWorkerThread;
import network.client.P2P.game.ServerPlayingWorkerThread;

import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Federica on 28/07/17.
 */

public class ExecutorServiceUtility {

    private int poolSize;
    private ExecutorService executor;
    private static Runnable workerServer;
    private static Runnable workerClient;
    private Future<?> future;
    private static ExecutorServiceUtility SINGLETON = null;

    private ExecutorServiceUtility(){

        this.poolSize = Runtime.getRuntime().availableProcessors()+1;
        this.executor = Executors.newFixedThreadPool(poolSize);
        this.future = null;
    }


    public static ExecutorServiceUtility getIstance(){
        if(SINGLETON == null){
            SINGLETON = new ExecutorServiceUtility();
        }
        return SINGLETON;
    }


    public void initServerPlayingWorkerThread(String ip, Registry registry, int rmiPort){
        workerServer = ServerPlayingWorkerThread.getIstance(this, registry, rmiPort);
        executor.execute(workerServer);
    }

    public void initClientPlayingWorkerThread(String ip, Registry registry){
        workerClient = new ClientPlayingWorkerThread(this, ip, registry);
        //this.future = executor.submit(new ClientPlayingWorkerThread(this, ip, registry));
        executor.execute(workerClient);
    }

    public void stopServerPlayingWorkerThread(){
        executor.shutdown();
        while (!executor.isTerminated()) {}
        System.out.println("All threads (server + clients have been shutdown");
    }

    public void stopClientPlayingWorkerThread(){
        this.future.cancel(true);

    }

    /*
    * TODO
    * compatto le varie future dei thread dei client
    * una volta compattate chiamo l'observable
    * e creo un flusso di flowable che metterò come
    * paramentro in ingresso a un metodo del mio
    * model / controller (devo ancora decidere)
    */






}
