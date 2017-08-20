/*
package network.client.P2P.game;

import client.model.Direction;
import client.model.PlayerImpl;
import network.client.P2P.bootstrap.ServerBootstrap;
import network.client.P2P.utils.ExecutorServiceUtility;

import java.rmi.Remote;
import java.rmi.RemoteException;

*/
/**
 * Created by Federica on 15/08/17.
 *//*

public class PeerRegisterImlp implements PeerRegister{

    private Remote obj;
    private ServerBootstrap serverBootstrap =   ServerBootstrap.getIstance(PlayerImpl.ip());
    private ServerPlayingWorkerThread server = ServerPlayingWorkerThread.getIstance(
            ExecutorServiceUtility.getIstance(), serverBootstrap.getRegistry(), serverBootstrap.getRmiPort());


    public PeerRegisterImlp(Remote obj){
        this.obj = obj;
    }



    @Override
    public Direction getPosition() throws RemoteException {
        return server.getPosition();

    }

    @Override
    public Boolean isAlive() throws RemoteException {

        return server.isAlive();
    }
}
*/
