package client.view.utils;

/**
 * Created by chiaravarini on 30/07/17.
 */
public class Range {

    private final int min;
    private final int max;

    public Range(final Integer min, final Integer max){
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
