package network.client.P2P.bootstrap;


/**
 * Created by Federica on 21/07/17.
 */
public class ClientBootstrap {


    public ClientBootstrap(String ip){

        System.setProperty("Djava.rmi.server.hostname", ip);

    }

}
