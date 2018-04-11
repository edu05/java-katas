package kata.bowling;

public class BowlingGame {

    private int score = 0;

    public void score(int pins) {
        score += pins;
    }

    public int getScore() {
        return score;
    }
}
