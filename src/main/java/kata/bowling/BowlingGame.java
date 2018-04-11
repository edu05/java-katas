package kata.bowling;

public class BowlingGame {

    private final int[] rolls = new int[21];
    private int rollCounter = 0;

    public void score(int pins) {
        rolls[rollCounter] = pins;
        rollCounter++;
    }

    public int getScore() {
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
