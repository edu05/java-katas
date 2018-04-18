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
        List<Cocktail> cocktails = asList(new Cocktail("my fancy cocktail", 3), new Cocktail("cocktail with a punch", 4));

        assertThat(cocktailBar.serve(cocktails), is(7));
    }
}