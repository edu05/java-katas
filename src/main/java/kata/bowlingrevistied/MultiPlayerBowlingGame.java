package kata.bowlingrevistied;

import java.util.ArrayList;
import java.util.List;

public class MultiPlayerBowlingGame {

    private final List<Integer> rolls = new ArrayList<>();

    public MultiPlayerBowlingGame(List<String> players) {
    }

    public void roll(int pins, String player) {
        rolls.add(pins);
    }

    public int getScore(String player) {
        int score = 0;
        int rollsLeft = 20;
        for (int i = 0; i < rollsLeft; i++) {
            if (rolls.get(i) == 10) {
                score += 10 + rolls.get(i + 1) + rolls.get(i + 2);
                rollsLeft--;
            } else if (i + 1 < rolls.size() && rolls.get(i) + rolls.get(i + 1) == 10) {
                score += 10 + rolls.get(i + 2);
                i++;
            } else {
                score += rolls.get(i);
            }
        }
        return score;
    }
}
