package client.view;

import client.model.Direction;
import client.model.Playground;

import java.awt.*;
import java.util.Map;

/**
 * Created by chiaravarini on 30/07/17.
 */
public interface SelectCharacterView {

    void charactersChoosen(final Map<String,Map<Direction,Image>> charactersMap);
    void playgroundChoosen(final Playground playground);
    void disableCharacter(final String nameImage);
    void enableCharacter(final String nameImage);
}
