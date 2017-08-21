package network.client.P2P.utils;

import network.client.P2P.game.ClientPlayingWorkerThread;
import network.client.P2P.game.ServerPlayingWorkerThread;

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


    public void initServerPlayingWorkerThread(String ip){

       // workerServer = ServerPlayingWorkerThread.getIstance(this);
        //executor.execute(workerServer);
        //new Thread(workerServer).start();
        ServerPlayingWorkerThread.getIstance(this).run();
    }

    public void initClientPlayingWorkerThread(String ip){
        workerClient = new ClientPlayingWorkerThread(this, ip);
        //this.future = executor.submit(new ClientPlayingWorkerThread(this, ip));
        executor.execute(workerClient);
    }

    public void stopPlayingWorkerThread(){
        executor.shutdown();
        while (!executor.isTerminated()) {}
    }

    //public void stopClientPlayingWorkerThread(){
    //    this.future.cancel(true);

    //}

    /*
    * TODO
    * compatto le varie future dei thread dei client
    * una volta compattate chiamo l'observable
    * e creo un flusso di flowable che metterò come
    * paramentro in ingresso a un metodo del mio
    * model / controller (devo ancora decidere)
    */






}
