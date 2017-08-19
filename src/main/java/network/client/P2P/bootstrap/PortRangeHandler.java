package network.client.P2P.bootstrap;

/**
 * Created by Federica on 20/07/17.
 *
 * a class to handle ports that have to be assigned to
 * peer servers so that they can start their rmiregistry
 *
 * NB. Usually, rmiregistry runs on port 1099 but, if this
 * port has already been taken, next port (1100) is assigned
 * to peer server and so on
 */

public class PortRangeHandler {

    private static final int MIN_PORT_VALUE =  1098;
    private static final int MAX_PORT_VALUE =  2100;
    private  int minimum;
    private  int maximum;

    public PortRangeHandler(){
        this.minimum = MIN_PORT_VALUE;
        this.maximum = MAX_PORT_VALUE;
    }


    //public static int getPortNumber(){
    //    return minimum;
    //}

    /*public static int getNextPortNumber(){
        int nextValue = minimum + 1;
        if(nextValue <= maximum) {
            minimum = nextValue;
        }
        return minimum;

    }*/

    public int getFirstAvailablePortNumber(){
        int nextValue = this.minimum + 1;
        if(nextValue <= this.maximum) {
            this.minimum = nextValue;
        }
        return this.minimum;
    }

}
