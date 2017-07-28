package network.client.P2P.main;

/**
 * Created by Federica on 27/07/17.
 */

/**
 * this is class main that has to be called from
 * client actors after they have finished to configure
 * game elements (such as character, playground) and
 * basic player info
 */
public class Main {

    public static void main(String[] args) {
        new MatchHandler();
    }
}
