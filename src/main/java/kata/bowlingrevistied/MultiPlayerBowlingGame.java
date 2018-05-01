package kata.bowlingrevistied;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiPlayerBowlingGame {

    private final Map<String, List<Integer>> rolls = new HashMap<>();

    public MultiPlayerBowlingGame(List<String> players) {
        players.forEach(player -> rolls.put(player, new ArrayList<>()));
    }

    public void roll(int pins, String player) {
        rolls.get(player).add(pins);
    }

    public int getScore(String player) {
        int score = 0;
        int rollsLeft = 20;
        List<Integer> playerRolls = this.rolls.get(player);
        for (int i = 0; i < rollsLeft; i++) {
            if (playerRolls.get(i) == 10) {
                score += 10 + playerRolls.get(i + 1) + playerRolls.get(i + 2);
                rollsLeft--;
            } else if (i + 1 < playerRolls.size() && playerRolls.get(i) + playerRolls.get(i + 1) == 10) {
                score += 10 + playerRolls.get(i + 2);
                i++;
            } else {
                score += playerRolls.get(i);
            }
        }
        return score;
    }
}
