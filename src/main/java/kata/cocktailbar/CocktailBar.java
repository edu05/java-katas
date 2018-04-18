package kata.cocktailbar;

import java.time.LocalTime;
import java.util.List;

public class CocktailBar {


    private static final LocalTime HAPPY_HOUR_START = LocalTime.of(21, 00);
    private static final LocalTime HAPPY_HOUR_END = LocalTime.of(22, 00);

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

            LocalTime now = LocalTime.now();
            if (now.isAfter(HAPPY_HOUR_START) && now.isBefore(HAPPY_HOUR_END)) {
                totalPrice += orderedCocktail.getPrice() / 2;
            } else {
                totalPrice += orderedCocktail.getPrice();
            }
        }
        return totalPrice;
    }
}
