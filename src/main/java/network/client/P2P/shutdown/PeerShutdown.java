package network.client.P2P.shutdown;

/**
 * Created by Federica on 27/07/17.
 */
public class PeerShutdown {


    /*
     * riferimento a executor riusciro ad averlo qui  perchè faccio una utils con gli executor.
     * utils per executor per 3 motivi:
     * -mi serve qui
     * -devo utilizzare due volte gli executor, perchè ho bisogno
     * di efficienza nella gestione dei thread sia nel bootstrap sia
     * nel playing => in questo modo, evito ripetizione di codice
     */

        /*PeerShutdown() {
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
            System.out.println("Finished all threads");
        }*/
}
