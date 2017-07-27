package network.client.P2P.bootstrap;

import network.client.P2P.game.PeerPlaying;
import network.client.P2P.shutdown.PeerShutdown;

/**
 * Created by Federica on 27/07/17.
 */
public class MatchHandler {

    private final PeerBootstrap bootstrap;
    private final PeerPlaying communication;
    private final PeerShutdown shutdown;

    public MatchHandler(){
        this.bootstrap = new PeerBootstrap();
        this.communication = new PeerPlaying();
        this.shutdown = new PeerShutdown();

    }
}
