package network.client;
/**
 * Created by Federica on 20/07/17.
 */

/**
 * a class to get a new port number available
 * everytime a new peer is created.
 * for a peer
 */


public class PortRangeGenerator {

    private static final int MIN_PORT_VALUE =  1099;
    private static final int MAX_PORT_VALUE =  2100;
    private static int minimum;
    private static int maximum;

    public PortRangeGenerator(){
        this.minimum = MIN_PORT_VALUE;
        this.maximum = MAX_PORT_VALUE;
    }


    /**
     * this method returns the next port number available
     * so that a peer can launch its rmiregistry on an
     * available port
     * @return
     */
    public static int getAvailablePortNumber(){
        int nextPort = minimum + 1;

        if(nextPort <=  maximum) {
            minimum = nextPort;
        }

        return minimum;
    }



}
