package P2P;

import network.client.P2P.bootstrap.PortRangeHandler;

import static org.junit.Assert.assertEquals;

/**
 * Created by Federica on 09/08/17.
 */
public class PortRangeHandlerTest {


    @org.junit.Test
    public void getFirstAvailablePortNumber() throws Exception {

        PortRangeHandler portRangeHandler = new PortRangeHandler();

        assertEquals(portRangeHandler.getFirstAvailablePortNumber(), 1099);
        assertEquals(portRangeHandler.getFirstAvailablePortNumber(), 1101);
        assertEquals(portRangeHandler.getFirstAvailablePortNumber(), 1102);
        assertEquals(portRangeHandler.getFirstAvailablePortNumber(), 1103);
        assertEquals(portRangeHandler.getFirstAvailablePortNumber(), 1104);
        assertEquals(portRangeHandler.getFirstAvailablePortNumber(), 1105);

    }

}