package kata.bowlingrevistied;

import java.util.List;

public class MultiPlayerBowlingGame {

    private final int[] rolls = new int[21];
    private int rollCounter = 0;

    public MultiPlayerBowlingGame(List<String> players) {
    }

    public void roll(int pins, String player) {
        rolls[rollCounter] = pins;
        rollCounter++;
    }

    public int getScore(String player) {
        int score = 0;
        int rollsLeft = 20;
        for (int i = 0; i < rollsLeft; i++) {
            if (rolls[i] == 10) {
                score += 10 + rolls[i + 1] + rolls[i + 2];
                rollsLeft--;
            } else if (rolls[i] + rolls[i + 1] == 10) {
                score += 10 + rolls[i + 2];
                i++;
            } else {
                score += rolls[i];
            }
        }
        return score;
    }
}
