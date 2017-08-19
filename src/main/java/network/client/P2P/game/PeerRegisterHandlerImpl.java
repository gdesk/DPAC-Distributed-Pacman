package network.client.P2P.game;

import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.RemoteException;

/**
 * Created by Federica on 16/08/17.
 */
public class PeerRegisterHandlerImpl implements  PeerRegisterHandler{

   // private final ServerBootstrap serverBootstrap = ServerBootstrap.getIstance(PlayerImpl.ip());
    private ServerPlayingWorkerThread serverPlayingWorkerThread = null;

    @Override
    public void updateRegisterObj() {
        if(serverPlayingWorkerThread == null) {

            serverPlayingWorkerThread = ServerPlayingWorkerThread
                    .getIstance(ExecutorServiceUtility.getIstance());
        }
        try {

            serverPlayingWorkerThread.updateObjects();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
