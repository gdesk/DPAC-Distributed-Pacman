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


    /**
     *
     * @param tris a list containing 3 elements (es. "ip", "currentScore", new PointImpl<2,4,())
     * @return an item whose type is Flowable<Object>
     */
    public Flowable<Object> createObservable(List<Object> tris){

        return Flowable.just(tris);
    }

}
