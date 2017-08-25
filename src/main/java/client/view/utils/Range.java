package client.view.utils;

/**
 * Wrapper for scala Range class
 * Created by Chiara Varini on 30/07/17.
 */
public class Range {

    private final int min;
    private final int max;

    public Range(final Integer min, final Integer max){
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return The smallest value in the range
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @return The largest value in the range
     */
    public int getMax() {
        return max;
    }
}
