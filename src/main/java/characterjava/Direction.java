package characterjava;

/**
 * This enumeration manages the character's movement.
 *
 * @author Giulia Lucchi
 */
public enum Direction {

    START("start"),
    RIGHT("right"),
    LEFT("left"),
    DOWN("down"),
    UP("up");

    private String direction;

    Direction(final String direction) {
        this.direction = direction;
    }

    /**
     *
     *  @return the character's direction
     * */
    public String getDirection() {
        return this.direction;
    }
}
