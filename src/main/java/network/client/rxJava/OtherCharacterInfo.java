package network.client.rxJava;

import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Federica on 03/08/17.
 *
 * questa classe è un punto di congiunzione
 * tra ClientPlayingWorkerThread e ObservableCharacter:
 * ogni volte che i client trovano informazioni che sono
 * state aggiornate, chiamano il metodo *createObservable*
 * che le converte da stringhe a flowable.
 */
public class OtherCharacterInfo {


    public Flowable<String> createObservable(List<String> list){

        return Flowable.fromIterable(list);
    }
}
