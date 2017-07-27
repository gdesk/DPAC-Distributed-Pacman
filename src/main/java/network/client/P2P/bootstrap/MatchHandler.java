package network.client.P2P.bootstrap;

/**
 * Created by Federica on 27/07/17.
 */
public class MatchHandler {

    private final PeerBootstrap bootstrap;
    private final PeerCommunication communication;
    private final PeerShutdown shutdown;

    public MatchHandler(){
        this.bootstrap = new PeerBootstrap();
        this.communication = new PeerCommunication();
        this.shutdown = new PeerShutdown();

    }
}
