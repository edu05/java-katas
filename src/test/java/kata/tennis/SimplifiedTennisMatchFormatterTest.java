package kata.tennis;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimplifiedTennisMatchFormatterTest {

    private static final String NADAL = "Nadal";
    private static final String FEDERER = "Federer";

    @Test
    public void testZeroZero() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("0 - 0")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(new FakeTennisMatch(NADAL, FEDERER, 0, 0)), is(expectedScoreString));
    }

    @Test
    public void testTennisPointNotation() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("30 - 15")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(new FakeTennisMatch(NADAL, FEDERER, 2, 1)), is(expectedScoreString));
    }

    @Test
    public void testPlayerThatHasWon() throws Exception {

        String expectedScoreString = "Nadal wins!";
        assertThat(SimplifiedTennisMatchFormatter.formatScore(new FakeTennisMatch(NADAL, FEDERER, 4, 0)), is(expectedScoreString));
    }

    @Test
    public void testNeedToWinByAMarginOf2Points() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("ADVANTAGE - ")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(new FakeTennisMatch(NADAL, FEDERER, 4, 3)), is(expectedScoreString));
    }

    @Test
    public void testDeuce() throws Exception {
        String expectedScoreString = new StringBuilder()
                .append("Nadal - Federer\n")
                .append("DEUCE - DEUCE")
                .toString();
        assertThat(SimplifiedTennisMatchFormatter.formatScore(new FakeTennisMatch(NADAL, FEDERER, 4, 4)), is(expectedScoreString));
    }
}