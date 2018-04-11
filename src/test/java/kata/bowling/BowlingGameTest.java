package kata.bowling;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BowlingGameTest {

    @Test
    public void testGutterGame() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();


        scoreNTimes(bowlingGame, 0, 20);

        assertThat(bowlingGame.getScore(), is(0));
    }

    @Test
    public void testSimpleGame() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();


        scoreNTimes(bowlingGame, 1, 20);

        assertThat(bowlingGame.getScore(), is(20));
    }

    @Test
    public void testSpareGame() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();


        scoreNTimes(bowlingGame, 5, 2);
        scoreNTimes(bowlingGame, 1, 18);

        assertThat(bowlingGame.getScore(), is(29));
    }

    @Test
    public void testSpareInFinalFrame() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();


        scoreNTimes(bowlingGame, 1, 18);
        scoreNTimes(bowlingGame, 5, 2);
        scoreNTimes(bowlingGame, 1, 1);

        assertThat(bowlingGame.getScore(), is(29));
    }

    @Test
    public void testStrike() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();


        scoreNTimes(bowlingGame, 10, 1);
        scoreNTimes(bowlingGame, 1, 18);

        assertThat(bowlingGame.getScore(), is(30));
    }

    @Test
    public void testMaxScore() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();


        scoreNTimes(bowlingGame, 10, 12);

        assertThat(bowlingGame.getScore(), is(300));
    }

    private void scoreNTimes(BowlingGame bowlingGame, int pins, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            bowlingGame.score(pins);
        }
    }
}