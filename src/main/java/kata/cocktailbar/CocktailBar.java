package kata.cocktailbar;

import java.time.LocalTime;
import java.util.List;

public class CocktailBar {

    private static final LocalTime HAPPY_HOUR_START = LocalTime.of(21, 00);
    private static final LocalTime HAPPY_HOUR_END = LocalTime.of(22, 00);

    private final Clock clock;

    public CocktailBar(Clock clock) {
        this.clock = clock;
    }

    public int serve(List<Cocktail> orderedCocktails) {
        if (orderedCocktails.size() >= 5) {
            throw new ShouldntGetDrunkException();
        }

        int totalPrice = 0;
        int oldFashionedsBought = 0;
        for (Cocktail orderedCocktail : orderedCocktails) {
            if (orderedCocktail.getDescription().equals(Cocktail.oldFashioned().getDescription())) {
                oldFashionedsBought++;
                if (oldFashionedsBought % 2 == 0) {
                    continue;
                }
            }

            LocalTime now = clock.getTime();
            if (now.isAfter(HAPPY_HOUR_START) && now.isBefore(HAPPY_HOUR_END)) {
                totalPrice += orderedCocktail.getPrice() / 2;
            } else {
                totalPrice += orderedCocktail.getPrice();
            }
        }

        return totalPrice;
    }
}
