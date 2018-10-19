package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Game {

    private static final int GUESSED_DIGIT_COUNT = 4;
    public boolean isWon;
    private final List<Integer> guessedDigits;
    private final List<Attempt> attempts;


    public Game() {
        guessedDigits = generateGuessedDigits();
        attempts = new ArrayList<>();
    }

    public void addAttempt(List<Integer> supposedDigits) {
        if (supposedDigits.isEmpty()) {
            throw new IllegalArgumentException("SupposedDigits are absent!");
        }
        if(isWon) {
            return;
        }
        int bullsCount = 0;
        int cowsCount = 0;
        for (int i = 0; i < supposedDigits.size(); i++) {
            if (Objects.equals(guessedDigits.get(i), supposedDigits.get(i))) {
                bullsCount++;
            } else if (guessedDigits.contains(supposedDigits.get(i))) {
                cowsCount++;
            }
        }
        Attempt attempt = new Attempt(supposedDigits, bullsCount, cowsCount);
        attempts.add(attempt);
        if(bullsCount==GUESSED_DIGIT_COUNT) {
            isWon=true;
        }
    }

    public int getAttemptsCount() {
        return this.attempts.size();
    }

    public boolean isWon() {
        return isWon;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public List<Integer> getGuessedDigits() {
        return guessedDigits;
    }

    private List<Integer> generateGuessedDigits() {
        Random random = new Random();
        List<Integer> guessedDigits = new ArrayList<>();
        while (guessedDigits.size() < GUESSED_DIGIT_COUNT) {
            int n = random.nextInt(10);
            if (!guessedDigits.contains(n)) {
                guessedDigits.add(n);
            }
        }
        return guessedDigits;
    }
}
