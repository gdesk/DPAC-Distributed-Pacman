package client.gameElement;

/**
 * Created by margherita on 07/07/17.
 */
public enum CharactersNames {
    Pacman        ("pacman"),
    RedGhost      ("red"),
    BlueGhost     ("blue"),
    GreenGhost    ("green"),
    YellowGhost   ("yellow"),
    PurpleGhost   ("purple");

    private final String name;

    CharactersNames(final String name) {
        this.name = name;
    }

  /*  public String getName() {
        return name;
    }*/

}
