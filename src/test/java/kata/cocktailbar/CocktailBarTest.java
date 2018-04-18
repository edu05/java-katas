package kata.cocktailbar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CocktailBarTest {

    @Test
    public void testEmptyOrderCostsNothing() throws Exception {
        CocktailBar cocktailBar = new CocktailBar();
        assertThat(cocktailBar.serve(new ArrayList<>()), is(0));
    }

    @Test
    public void testAddingPrices() throws Exception {
        CocktailBar cocktailBar = new CocktailBar();
        List<Cocktail> cocktails = asList(Cocktail.mojito(), Cocktail.bloodyMary());

        assertThat(cocktailBar.serve(cocktails), is(22));
    }

    @Test
    public void testPriceForSingleOldFashioned() throws Exception {
        CocktailBar cocktailBar = new CocktailBar();
        List<Cocktail> cocktails = asList(Cocktail.oldFashioned());

        assertThat(cocktailBar.serve(cocktails), is(11));
    }

    @Test
    public void testPriceForTwoOldFashioned() throws Exception {
        CocktailBar cocktailBar = new CocktailBar();
        List<Cocktail> cocktails = asList(Cocktail.oldFashioned(), Cocktail.oldFashioned());

        assertThat(cocktailBar.serve(cocktails), is(11));
    }

    @Test
    public void testHappyHour() throws Exception {
        //???

    }
}