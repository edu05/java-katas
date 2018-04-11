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
        assertThat(match.isFinishedGame(), is(false));
    }

    @Test
    public void testPlayersScoring() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        match.score(NADAL);
        match.score(NADAL);
        match.score(FEDERER);

        assertThat(match.getPlayer1Points(), is(2));
        assertThat(match.getPlayer2Points(), is(1));
        assertThat(match.isFinishedGame(), is(false));
    }

    @Test
    public void testSimpleWin() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        nadalScore(match, 7);
        assertThat(match.isFinishedGame(), is(true));
        assertThat(match.getWinner().get(), is(NADAL));
    }

    @Test
    public void testPlayersCantScoreAfterSimpleWin() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        try {
            nadalScore(match, 8);
            fail();
        } catch (RuntimeException e) {
        }
    }

    @Test
    public void testGameNeedsToBeWonBy3Points() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        nadalScore(match, 6);
        federerScore(match, 6);
        nadalScore(match, 2);

        assertThat(match.getPlayer1Points(), is(8));
        assertThat(match.getPlayer2Points(), is(6));
        assertThat(match.isFinishedGame(), is(false));
        assertThat(match.getWinner().isPresent(), is(false));
    }

    @Test
    public void testPlayersCanScoreToWinAfterDeuce() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        nadalScore(match, 6);
        federerScore(match, 6);
        nadalScore(match, 3);

        assertThat(match.getPlayer1Points(), is(9));
        assertThat(match.getPlayer2Points(), is(6));
        assertThat(match.isFinishedGame(), is(true));
        assertThat(match.getWinner().get(), is(NADAL));
    }

    @Test
    public void testPlayersCanScoreWhileThreePointAdvantageIsNotObtained() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        nadalScore(match, 3);
        federerScore(match, 3);
        nadalScore(match, 2);
        federerScore(match, 5);

        assertThat(match.getPlayer1Points(), is(5));
        assertThat(match.getPlayer2Points(), is(8));
        assertThat(match.isFinishedGame(), is(true));
        assertThat(match.getWinner().get(), is(FEDERER));
    }

    private void federerScore(SimplifiedTennisMatch match, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            match.score(FEDERER);
        }
    }

    private void nadalScore(SimplifiedTennisMatch match, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            match.score(NADAL);
        }
    }
}