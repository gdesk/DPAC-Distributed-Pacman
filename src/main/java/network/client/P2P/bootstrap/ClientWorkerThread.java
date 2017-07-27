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


    public ClientWorkerThread(String ip) throws UnknownHostException {
        this.ip = ip;
        this.message   = new JSONObject();

    }

    @Override
    public void run() {

        if(startClients()) {
            System.setProperty("Djava.rmi.server.hostname", ip);
            String host = ip;

            try {
                Registry registry = null;
                try {
                    registry = LocateRegistry.getRegistry(host);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

                PeerStateRegister stub = (PeerStateRegister) registry.lookup("Hello from M");
                //currentWorking.PeerStateRegister stub = (currentWorking.PeerStateRegister) registry.lookup("Hello from F");
                String response = stub.sayHello();
                System.out.println("response: " + response);


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
    private boolean startClients() {
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
}
