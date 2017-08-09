package client.view;

import client.model.Direction;
import client.model.character.Character;

/**
 * Created by chiaravarini on 29/07/17.
 */
public interface GamePanel {

    void updateLives(final Character character);
    void deleteCharacter(final Character character);
    void updateScore(final int score);
    void move(final CharacterView characterView, final Direction direction);
}
