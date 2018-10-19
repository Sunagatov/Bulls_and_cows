package main.models;

import java.io.Serializable;

public class User implements Serializable {
    private String gameNick;
    private GameStatistics gameStatistics;

    public User(String gameNick, GameStatistics gameStatistics) {
        this.gameNick = gameNick;
        this.gameStatistics = gameStatistics;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    public String getGameNick() {
        return gameNick;
    }
}
