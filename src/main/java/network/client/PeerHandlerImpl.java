package network.client;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * Created by Federica on 12/07/17.
 *
 * This class is useful to handle configuration of
 * every peer so, everytime a peer needs to send a
 * message to other peers, the first one is configured
 * as a server, instead, the other one as clients
 */

public class PeerHandlerImpl implements PeerHandler{

    final private Set<Peer> peers;


    public PeerHandlerImpl(final Set<Peer> peers){
        this.peers = peers;

    }

    /**
     * this method configs every peer on the network,
     * setting its behaviour like client or server
     * @param peer which acts like server
     */
    public void peersConfig(Peer peer){
        int serverPort = peer.getPort();
        for (Peer p: peers){
            if(p.equals(peer)){
                try {
                    p.initServer();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }else{
                p.initClient(serverPort);
            }
        }
    }


}
