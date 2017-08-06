package network.client.P2P.bootstrap;


import java.net.UnknownHostException;

/**
 * Created by Federica on 21/07/17.
 */
public class ClientBootstrap {



    private String ip;


    public ClientBootstrap(String ip) throws UnknownHostException {

        this.ip = ip;

        System.setProperty("Djava.rmi.server.hostname", this.ip);

    }

}
