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

        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("0 - 0")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(match), is(expectedScoreString));
    }

    @Test
    public void testPlayersScoring() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        match.score(NADAL);
        match.score(NADAL);
        match.score(FEDERER);

        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("30 - 15")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(match), is(expectedScoreString));
    }

    @Test
    public void testPlayerWins() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        match.score(NADAL);
        match.score(NADAL);
        match.score(NADAL);
        match.score(NADAL);

        String expectedScoreString = "Nadal wins!";
        assertThat(SimplifiedTennisMatchFormatter.formatScore(match), is(expectedScoreString));
    }

    @Test
    public void testNeedToWinByAMarginOf2Points() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        match.score(NADAL);
        match.score(NADAL);
        match.score(NADAL);
        match.score(FEDERER);
        match.score(FEDERER);
        match.score(FEDERER);
        match.score(NADAL);

        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("ADVANTAGE - ")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(match), is(expectedScoreString));
    }

    @Test
    public void testDeuce() throws Exception {
        SimplifiedTennisMatch match = new SimplifiedTennisMatch(NADAL, FEDERER);

        match.score(NADAL);
        match.score(NADAL);
        match.score(NADAL);
        match.score(FEDERER);
        match.score(FEDERER);
        match.score(FEDERER);
        match.score(FEDERER);
        match.score(NADAL);

        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("DEUCE - DEUCE")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(match), is(expectedScoreString));
    }
}