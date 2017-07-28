package network.client.P2P.bootstrap;


import org.json.JSONObject;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Federica on 21/07/17.
 */
public class ClientWorkerThread implements Runnable {

    private String ip;
    private final JSONObject message;
    private static Registry registry;

    public ClientWorkerThread(String ip) throws UnknownHostException {
        this.ip = ip;
        this.message   = new JSONObject();
        registry = null;

    }

    @Override
    public void run() {

        if(isClientStarted()) {
            System.setProperty("Djava.rmi.server.hostname", ip);
            String host = ip;

            try {

                try {
                    registry = LocateRegistry.getRegistry(host);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }



            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

    }



    /**
     * this method checks if client threads can
     * start running on this peer. Before do that,
     * they have to wait until every peer server
     * has been properly configured.
     * @return
     */
    private boolean isClientStarted() {
        //TODO
        /*try {
            if(this.inbox.toString().equals(PeerMessages.CLIENTS_CAN_START_RUNNING)){
                this.inbox.receive(FiniteDuration.apply(10, TimeUnit.SECONDS));
                return true;
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        }*/

        return false;
    }

    public static Registry getRegistry(){
        return registry;
    }
}
