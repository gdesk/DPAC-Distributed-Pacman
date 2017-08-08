package network.client.rxJava;

import client.model.peerCommunication.ClientIncomingMessageHandlerImpl;

import java.util.List;

/**
 * Created by Federica on 03/08/17.
 *
 *  this class implementing an Observable producing
 *  a flow which contains data coming from other
 *  characters (aka peers).
 *
 *  NB. This data has been collected through RMI
 *
 */



public class ObservableCharacter {

    private ClientIncomingMessageHandlerImpl handler;
    private OtherCharacterInfo info;

    public ObservableCharacter(){
        this.handler = new ClientIncomingMessageHandlerImpl();
        this.info = new OtherCharacterInfo();
    }

    public void subscribeObserver(List<Object> tris){

        if(tris.size() == 3){
            this.info.createObservable(tris).subscribe((item) -> {
                this.handler.updateGameView(item);
            });

        }

    }

}
