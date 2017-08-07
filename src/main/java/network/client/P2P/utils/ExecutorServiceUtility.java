package network.client.P2P.utils;

import client.model.peerCommunication.ClientIncomingMessageHandlerImpl;
import network.client.P2P.game.ClientPlayingWorkerThread;
import network.client.P2P.game.ServerPlayingWorkerThread;
import network.client.rxJava.OtherCharacterInfo;

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
    private Runnable worker;
    private Future<?> future;


    public ExecutorServiceUtility(){

        this.poolSize = Runtime.getRuntime().availableProcessors()+1;
        this.executor = Executors.newFixedThreadPool(poolSize);
        this.worker = null;
        this.future = null;
    }


    public void initServerPlayingWorkerThread(String ip, Registry registry, int rmiPort){
        this.worker = ServerPlayingWorkerThread.getIstance(this, ip, registry, rmiPort);
        executor.execute(worker);
    }

    public void initClientPlayingWorkerThread(String ip, Registry registry, OtherCharacterInfo info,
                                              ClientIncomingMessageHandlerImpl handler){
        //this.worker = new ClientPlayingWorkerThread(this);
        this.future = executor.submit(new ClientPlayingWorkerThread(this, ip, registry, info, handler));

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

    /*
    * TODO
    * compatto le varie future dei thread dei client
    * una volta compattate chiamo l'observable
    * e creo un flusso di flowable che metterò come
    * paramentro in ingresso a un metodo del mio
    * model / controller (devo ancora decidere)
    */






}
