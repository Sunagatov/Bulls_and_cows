package main.models;

import java.util.List;

public class Attempt {
    private List<Integer> supposedDigits;
    private int bullsCount;
    private int cowsCount;

    public Attempt(List<Integer> supposedDigits, int bullsCount, int cowsCount) {
        this.supposedDigits = supposedDigits;
        this.bullsCount = bullsCount;
        this.cowsCount = cowsCount;
    }

    public List<Integer> getSupposedDigits() {
        return supposedDigits;
    }

    public int getBullsCount() {
        return bullsCount;
    }

    public int getCowsCount() {
        return cowsCount;
    }
}
