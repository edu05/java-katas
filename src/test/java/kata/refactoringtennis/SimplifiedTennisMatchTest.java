package kata.refactoringtennis;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimplifiedTennisMatchTest {

    private static final String NADAL = "Nadal";
    private static final String FEDERER = "Federer";
    private SimplifiedTennisMatch match;

    @Before
    public void setUp() throws Exception {
        match = new SimplifiedTennisMatch(NADAL, FEDERER);
    }

    @Test
    public void testUnstartedMatch() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("0 - 0")
                .toString();
        assertThat(match.formatScore(), is(expectedScoreString));
    }

    @Test
    public void testPlayersScoring() throws Exception {
        nadalScore(2);
        federerScore(1);

        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("30 - 15")
                .toString();

        assertThat(match.formatScore(), is(expectedScoreString));
    }

    @Test
    public void testPlayerWins() throws Exception {
        nadalScore(4);

        String expectedScoreString = "Nadal wins!";
        assertThat(match.formatScore(), is(expectedScoreString));
    }

    @Test
    public void testNeedToWinByAMarginOf2Points() throws Exception {
        nadalScore(3);
        federerScore(3);
        nadalScore(1);

        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("ADVANTAGE - ")
                .toString();
        assertThat(match.formatScore(), is(expectedScoreString));
    }

    @Test
    public void testDeuce() throws Exception {
        nadalScore(3);
        federerScore(4);
        nadalScore(1);

        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("DEUCE")
                .toString();
        assertThat(match.formatScore(), is(expectedScoreString));
    }


    private void federerScore(int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            match.score(FEDERER);
        }
    }

    private void nadalScore(int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            match.score(NADAL);
        }
    }
}