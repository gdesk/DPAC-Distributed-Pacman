package client.gameElement;

/**
 * Created by Manuel Bottax on 30/06/2017.
 */
public enum Fruits {
    CHERRY       (100),
    STRAWBERRY   (300),
    ORANGE       (500),
    APPLE        (700),
    GRAPES       (1000),
    GALAXIAN_SHIP(2000),
    BELL         (3000),
    KEY          (5000);

    private final int score;

    Fruits(int score) {
        this.score = score;
    }

    public int getScore () {
        return this.score;
    }

}
