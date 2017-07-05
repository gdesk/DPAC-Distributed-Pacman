package view.utils;

/**
 * Created by chiaravarini on 05/07/17.
 */
public enum BlocksImages {

    HORIZONTAL("horizontalBlock"),
    HORIZONTAL_BOTTOM("HorizontalBottom"),
    HORIZONTAL_UP("HorizontalUp"),

    LEFT_END("leftEnd"),
    LOWER_END("lowerEnd"),
    RIGHT_END("rightEnd"),
    UPPER_END("upperEnd"),

    VERTICAL("verticalBlock"),
    VERTICAL_LEFT("verticalLeft"),
    VERTICAL_RIGHT("verticalRight"),

    LOWER_LEFT_CORNER("lowerLeftCorner"),
    LOWER_RIGHT_CORNER("lowerRightCorner"),
    UPPER_LEFT_CORNER("upperLeftCorner"),
    UPPER_RIGHT_CORNER("upperRightCorner");

    private final String imageFileName;

    BlocksImages(final String imageFileName){
        this.imageFileName = imageFileName;
    }

    public String getImageFileName(){
        return this.imageFileName;
    }
}

