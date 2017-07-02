package client.gameElement;

/**
 * Created by Manuel Bottax on 30/06/2017.
 */
public enum Fruits {
    Cherry       (100),
    Strawberry   (300),
    Orange       (500),
    Apple        (700),
    Grapes       (1000),
    GalaxianShip (2000),
    Bell         (3000),
    Key          (5000);

    private final int score;

    Fruits(int score) {
        this.score = score;
    }

    public int getScore () {
        return this.score;
    }

}
