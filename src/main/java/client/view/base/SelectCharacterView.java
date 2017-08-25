package client.view.base;

/**
 * Panel interface where you can choose
 * the desired character and game playground
 *
 * Created by Chiara Varini on 30/07/17.
 */
public interface SelectCharacterView {

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

    /**
     * This method allows you to set the number of player
     * so that only the number of characters needed is displayed
     */
    void setNumPlayer(final int numPlayer);
}
