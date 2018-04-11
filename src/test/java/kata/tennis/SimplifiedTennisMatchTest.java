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
        assertThat(match.formatScore(), is(expectedScoreString));
    }
}