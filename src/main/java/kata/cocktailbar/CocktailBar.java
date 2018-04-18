package kata.cocktailbar;

import java.util.List;

public class CocktailBar {

    public int serve(List<Cocktail> orderedCocktails) {
        int totalPrice = 0;
        for (Cocktail orderedCocktail : orderedCocktails) {
            totalPrice += orderedCocktail.getPrice();
        }
        return totalPrice;
    }
}
