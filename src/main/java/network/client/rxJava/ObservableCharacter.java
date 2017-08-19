package network.client.rxJava;

import client.model.peerCommunication.ClientIncomingMessageHandlerImpl;
import io.reactivex.Flowable;

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

    private ClientIncomingMessageHandlerImpl characterHandler;
    private OtherCharacterInfo info;

    public ObservableCharacter() {
        this.characterHandler = new ClientIncomingMessageHandlerImpl();

        this.info = new OtherCharacterInfo();
    }

    public void subscribeObserver(List<Object> list) {

        Flowable.just(list.get(0), list.get(1), list.get(2))
                .subscribe((item) -> {
                    this.characterHandler.updateGameView(item);
                });
    }
}