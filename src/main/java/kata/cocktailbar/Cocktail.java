package kata.cocktailbar;

public class Cocktail {
    private final String description;
    private final int price;

    private Cocktail(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public static Cocktail mojito() {
        return new Cocktail("traditional Cuban highball", 10);
    }

    public static Cocktail godfather() {
        return new Cocktail("scotch single malt and amaretto", 9);
    }

    public static Cocktail bloodyMary() {
        return new Cocktail("vodka meets tomato juice and other spices", 12);
    }

    public static Cocktail oldFashioned() {
        return new Cocktail("whisky muddled with sugar and bitters", 11);
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
