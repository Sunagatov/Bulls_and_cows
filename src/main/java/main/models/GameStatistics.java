package main.models;

import java.io.Serializable;

public class GameStatistics implements Serializable {
    private final int gamesCount;
    private final int attemptsCount;

    public GameStatistics(int gamesCount, int attemptsCount) {
        this.gamesCount = gamesCount;
        this.attemptsCount = attemptsCount;
    }

    public double getAverageAttemptsCount() {
        if (gamesCount == 0) {
            return 0;
        }
        return (double) attemptsCount / gamesCount;
    }

    public int getGamesCount() {
        return gamesCount;
    }
}
