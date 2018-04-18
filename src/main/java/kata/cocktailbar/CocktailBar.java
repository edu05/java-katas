package kata.cocktailbar;

import java.util.List;

public class CocktailBar {


    public int serve(List<Cocktail> orderedCocktails) {
        int totalPrice = 0;
        int oldFashionedsBought = 0;
        for (Cocktail orderedCocktail : orderedCocktails) {
            if (orderedCocktail.getDescription().equals(Cocktail.oldFashioned().getDescription())) {
                oldFashionedsBought++;
                if (oldFashionedsBought % 2 == 0) {
                    continue;
                }
            }
            totalPrice += orderedCocktail.getPrice();
        }
        return totalPrice;
    }
}
