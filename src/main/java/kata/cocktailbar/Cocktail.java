package kata.cocktailbar;

public class Cocktail {
    private final String description;
    private final int price;

    public Cocktail(String description, int price) {

        this.description = description;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
