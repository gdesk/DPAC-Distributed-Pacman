package client.view;

import client.model.Direction;
import client.model.Playground;

import java.awt.*;
import java.util.Map;

/**
 * Panel interface where you can choose
 * the desired character and game playground
 *
 * Created by Chiara Varini on 30/07/17.
 */
public interface SelectCharacterView {

    /**
     * This method
     * @param charactersMap
     */
    void charactersChoosen(final Map<String,Map<Direction,Image>> charactersMap);

    /**
     *
     * @param playground
     */
    void playgroundChoosen(final Playground playground);

    /**
     * This method allows you to disable the image of a character
     * already selected by another player
     * @param nameImage
     */
    void disableCharacter(final String nameImage);

    /**
     * This method allows you to rehabilitate the image
     * of a previously disabled character
     * @param nameImage
     */
    void enableCharacter(final String nameImage);
}
