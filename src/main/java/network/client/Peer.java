package network.client;import java.net.UnknownHostException;

/**
 * Created by Federica on 12/07/17.
 *
 * an interface which allows to configure
 * each peer properly
 */

public interface Peer {

    /**
     * a method to config peer as a server,
     * in case it has to send a message
     * @throws UnknownHostException
     */
    void initServer() throws UnknownHostException;

    /**
     * a method to config peer as a client,
     * in case it has to receive a message
     * @param serverPort
     */
    void initClient(int serverPort);

    /**
     * setter for message in case the peer
     * is a server
     * @param message
     */
    void setMessage(String message);

    /**
     * getter to return helpful to configure the socket on which
     * client has to listen (in order to receive packets)
     * @return
     */
    int getPort();



}
