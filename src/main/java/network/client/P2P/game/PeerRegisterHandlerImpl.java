package network.client.P2P.game;

import client.model.PlayerImpl;
import network.client.P2P.bootstrap.ServerBootstrap;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.RemoteException;

/**
 * Created by Federica on 16/08/17.
 */
public class PeerRegisterHandlerImpl implements  PeerRegisterHandler{

    private final ServerBootstrap serverBootstrap = ServerBootstrap.getIstance(PlayerImpl.ip());
    private ServerPlayingWorkerThread serverPlayingWorkerThread = null;

    @Override
    public void updateRegisterObj() {
        if(serverPlayingWorkerThread == null) {
            serverPlayingWorkerThread = ServerPlayingWorkerThread
                    .getIstance(ExecutorServiceUtility.getIstance(), serverBootstrap.getRegistry(), serverBootstrap.getRmiPort());
        }
        try {
            serverPlayingWorkerThread.updateObjects();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
