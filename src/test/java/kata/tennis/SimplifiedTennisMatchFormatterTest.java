package kata.tennis;

import org.junit.Test;

import static kata.tennis.FakeTennisMatch.deuceMatch;
import static kata.tennis.FakeTennisMatch.firstPlayerAdvantageMatch;
import static kata.tennis.FakeTennisMatch.player1WonFinishedMatch;
import static kata.tennis.FakeTennisMatch.unsfinishedMatch;
import static kata.tennis.FakeTennisMatch.unstartedMatch;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimplifiedTennisMatchFormatterTest {

    private static final String NADAL = "Nadal";
    private static final String FEDERER = "Federer";

    @Test
    public void testUnstartedMatch() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("0 - 0")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(unstartedMatch(NADAL, FEDERER)), is(expectedScoreString));
    }

    @Test
    public void testTennisPointNotation() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("30 - 15")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(unsfinishedMatch(NADAL, FEDERER, 2, 1)), is(expectedScoreString));
    }

    @Test
    public void testPlayerThatHasWon() throws Exception {

        String expectedScoreString = "Nadal wins!";
        assertThat(SimplifiedTennisMatchFormatter.formatScore(player1WonFinishedMatch(NADAL, FEDERER)), is(expectedScoreString));
    }

    @Test
    public void testAdvance() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("ADVANTAGE - ")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(firstPlayerAdvantageMatch(NADAL, FEDERER)), is(expectedScoreString));
    }

    @Test
    public void testDeuce() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("DEUCE - DEUCE")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(deuceMatch(NADAL, FEDERER)), is(expectedScoreString));
    }
}