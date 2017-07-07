package client.gameElement;

/**
 * Created by margherita on 07/07/17.
 */
public enum Eatables {
    Dot          ("dot",10),
    Pill         ("pill",50),
    Cherry       ("fruit",100),
    Strawberry   ("fruit",300),
    Orange       ("fruit",500),
    Apple        ("fruit",700),
    Grapes       ("fruit",1000),
    GalaxianShip ("fruit",2000),
    Bell         ("fruit",3000),
    Key          ("fruit",5000);

    private final String family;
    private final int score;

    Eatables(final String family, final int score) {
        this.family = family;
        this.score = score;
    }

    public String getFamily() {
        return family;
    }

    public int getScore() {
        return score;
    }

}
