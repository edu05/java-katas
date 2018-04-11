package kata.bowling;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BowlingGameTest {

    @Test
    public void testGutterGame() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();


        for (int i = 0; i < 20; i++) {
            bowlingGame.score(0);
        }

        assertThat(bowlingGame.getScore(), is(0));
    }
}