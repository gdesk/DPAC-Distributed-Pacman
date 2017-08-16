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
    private final ServerPlayingWorkerThread serverPlayingWorkerThread = ServerPlayingWorkerThread
            .getIstance(ExecutorServiceUtility.getIstance(), serverBootstrap.getRegistry(), serverBootstrap.getRmiPort());


    @Override
    public void updateRegisterObj() {
        try {
            serverPlayingWorkerThread.updateObjects();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
