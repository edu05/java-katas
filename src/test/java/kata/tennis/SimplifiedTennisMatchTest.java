package kata.tennis;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

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

    @Test
    public void testPlayersCantScoreAfterSimpleWin() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        try {
            match.score(NADAL);
            match.score(NADAL);
            match.score(NADAL);
            match.score(NADAL);
            match.score(NADAL);
            fail();
        } catch (RuntimeException e) {
        }
    }

    @Test
    public void testPlayersCanScoreToWinAfterDeuce() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

            match.score(NADAL);
            match.score(NADAL);
            match.score(NADAL);
            match.score(FEDERER);
            match.score(FEDERER);
            match.score(FEDERER);
            match.score(NADAL);
            match.score(NADAL);

        assertThat(match.getPlayer1Points(), is(5));
        assertThat(match.getPlayer2Points(), is(3));
        assertThat(match.isFinishedGame(), is(true));
    }

    @Test
    public void testPlayersCanScoreWhileTwoPointAdvantageIsNotObtained() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        match.score(NADAL);
        match.score(NADAL);
        match.score(NADAL);
        match.score(FEDERER);
        match.score(FEDERER);
        match.score(FEDERER);
        match.score(NADAL);
        match.score(FEDERER);
        match.score(NADAL);
        match.score(FEDERER);
        match.score(FEDERER);

        assertThat(match.getPlayer1Points(), is(5));
        assertThat(match.getPlayer2Points(), is(6));
        assertThat(match.isFinishedGame(), is(false));
    }
}