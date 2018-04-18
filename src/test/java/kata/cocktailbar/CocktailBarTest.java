package kata.cocktailbar;

import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CocktailBarTest {

    private Clock clock = mock(Clock.class);

    @Test
    public void testEmptyOrderCostsNothing() throws Exception {
        when(clock.getTime()).thenReturn(LocalTime.of(13,00));
        CocktailBar cocktailBar = new CocktailBar(clock);
        assertThat(cocktailBar.serve(new ArrayList<>()), is(0));
    }

    @Test
    public void testAddingPrices() throws Exception {
        when(clock.getTime()).thenReturn(LocalTime.of(13,00));
        CocktailBar cocktailBar = new CocktailBar(clock);
        List<Cocktail> cocktails = asList(Cocktail.mojito(), Cocktail.bloodyMary());

        assertThat(cocktailBar.serve(cocktails), is(22));
    }

    @Test
    public void testPriceForSingleOldFashioned() throws Exception {
        when(clock.getTime()).thenReturn(LocalTime.of(13,00));
        CocktailBar cocktailBar = new CocktailBar(clock);
        List<Cocktail> cocktails = asList(Cocktail.oldFashioned());

        assertThat(cocktailBar.serve(cocktails), is(11));
    }

    @Test
    public void testPriceForTwoOldFashioned() throws Exception {
        when(clock.getTime()).thenReturn(LocalTime.of(13,00));
        CocktailBar cocktailBar = new CocktailBar(clock);
        List<Cocktail> cocktails = asList(Cocktail.oldFashioned(), Cocktail.oldFashioned());

        assertThat(cocktailBar.serve(cocktails), is(11));
    }

    @Test
    public void testHappyHour() throws Exception {
        CocktailBar cocktailBar = new CocktailBar(new AlwaysHappyHourClock());

        List<Cocktail> cocktails = asList(Cocktail.mojito());

        assertThat(cocktailBar.serve(cocktails), is(5));

    }

    @Test
    public void testHappyHourWithMocks() throws Exception {
        Clock fakeClock = mock(Clock.class);
        when(fakeClock.getTime()).thenReturn(LocalTime.of(21, 30));
        CocktailBar cocktailBar = new CocktailBar(fakeClock);

        List<Cocktail> cocktails = asList(Cocktail.mojito());

        assertThat(cocktailBar.serve(cocktails), is(5));
    }
}