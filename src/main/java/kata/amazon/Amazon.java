package kata.amazon;

public class Amazon {


    private final Warehouse warehouse;

    public Amazon(Warehouse warehouse, Wallet wallet) {
        this.warehouse = warehouse;
    }

    public void buy(Customer customer, Item item) {
        warehouse.dispatch(customer, item);
    }
}
