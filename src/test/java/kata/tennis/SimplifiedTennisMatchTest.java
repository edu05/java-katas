package kata.tennis;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimplifiedTennisMatchTest {

    private static final String NADAL = "Nadal";
    private static final String FEDERER = "Federer";

    @Test
    public void testUnstartedMatch() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        assertThat(match.getPlayer1(), is(NADAL));
        assertThat(match.getPlayer2(), is(FEDERER));
        assertThat(match.getPlayer1Points(), is(0));
        assertThat(match.getPlayer2Points(), is(0));
    }

    @Test
    public void testPlayersScoring() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        match.score(NADAL);
        match.score(NADAL);
        match.score(FEDERER);

        assertThat(match.getPlayer1Points(), is(2));
        assertThat(match.getPlayer2Points(), is(1));
    }
}