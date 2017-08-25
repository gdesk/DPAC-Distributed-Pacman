package client.view.utils.enumerations;

/**
 * Enumeration for different fruits game images
 * Created by Chiara Varini on 03/07/17.
 */
public enum FruitsImages {

    APPLE("apple"),
    BELL("bell"),
    CHERRY("cherry"),
    GALAXIAN("galaxian"),
    GRAPES("grapes"),
    KEY("key"),
    ORANGE("orange"),
    STRAWBERRY("strawberry");

    private final String imageFileName;

    FruitsImages(final String imageFileName){
        this.imageFileName = imageFileName;
    }

    /**
     *
     * @return The FruitImages in the form of a String
     */
    public String getImageFileName(){
        return this.imageFileName;
    }
}
