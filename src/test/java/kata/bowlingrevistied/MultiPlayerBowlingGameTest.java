package kata.bowlingrevistied;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MultiPlayerBowlingGameTest {

    private static final String NADAL = "Nadal";
    private static final String FEDERER = "Federer";

    @Test
    public void testGutterGame() throws Exception {
        MultiPlayerBowlingGame bowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL));


        scoreNTimes(bowlingGame, 0, 20);

        assertThat(bowlingGame.getScore(NADAL), is(0));
    }

    @Test
    public void testSimpleGame() throws Exception {
        MultiPlayerBowlingGame bowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL));


        scoreNTimes(bowlingGame, 1, 20);

        assertThat(bowlingGame.getScore(NADAL), is(20));
    }

    @Test
    public void testSpareGame() throws Exception {
        MultiPlayerBowlingGame bowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL));


        scoreNTimes(bowlingGame, 5, 2);
        scoreNTimes(bowlingGame, 1, 18);

        assertThat(bowlingGame.getScore(NADAL), is(29));
    }

    @Test
    public void testSpareInFinalFrame() throws Exception {
        MultiPlayerBowlingGame bowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL));


        scoreNTimes(bowlingGame, 1, 18);
        scoreNTimes(bowlingGame, 5, 2);
        scoreNTimes(bowlingGame, 1, 1);

        assertThat(bowlingGame.getScore(NADAL), is(29));
    }

    @Test
    public void testStrike() throws Exception {
        MultiPlayerBowlingGame bowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL));


        scoreNTimes(bowlingGame, 10, 1);
        scoreNTimes(bowlingGame, 1, 18);

        assertThat(bowlingGame.getScore(NADAL), is(30));
    }

    @Test
    public void testMaxScore() throws Exception {
        MultiPlayerBowlingGame bowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL));


        scoreNTimes(bowlingGame, 10, 12);

        assertThat(bowlingGame.getScore(NADAL), is(300));
    }

    private void scoreNTimes(MultiPlayerBowlingGame bowlingGame, int pins, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            bowlingGame.roll(pins, NADAL);
        }
    }

    @Test
    public void testTwoPlayersScoring() throws Exception {
        MultiPlayerBowlingGame multiPlayerBowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL, FEDERER));

        for (int i = 0; i < 20; i++) {
            multiPlayerBowlingGame.roll(2, NADAL);
            multiPlayerBowlingGame.roll(1, FEDERER);
        }

        assertThat(multiPlayerBowlingGame.getScore(NADAL), is(40));
        assertThat(multiPlayerBowlingGame.getScore(FEDERER), is(20));
    }

    @Test(expected = InvalidPlayerException.class)
    public void testInvalidPlayerCantRoll() throws Exception {
        MultiPlayerBowlingGame multiPlayerBowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL, FEDERER));

        multiPlayerBowlingGame.roll(5, "edu");
    }
}