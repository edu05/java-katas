package kata.bowlingrevistied;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MultiPlayerBowlingGameTest {

    private static final String NADAL = "Nadal";
    private static final String FEDERER = "Federer";

    @Test
    public void testTwoPlayersScoring() throws Exception {
        MultiPlayerBowlingGame multiPlayerBowlingGame = new MultiPlayerBowlingGame(Arrays.asList(NADAL, FEDERER));

        for (int i = 0; i < 20; i++) {
            multiPlayerBowlingGame.roll(1, NADAL);
            multiPlayerBowlingGame.roll(1, FEDERER);
        }

        assertThat(multiPlayerBowlingGame.getScore(NADAL), is(20));
        assertThat(multiPlayerBowlingGame.getScore(FEDERER), is(20));
    }
}