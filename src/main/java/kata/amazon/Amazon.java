package kata.amazon;

public class Amazon {


    private final Warehouse warehouse;
    private final Wallet wallet;

    public Amazon(Warehouse warehouse, Wallet wallet) {
        this.warehouse = warehouse;
        this.wallet = wallet;
    }

    public void buy(Customer customer, Item item) {
        wallet.bill(customer, item.getPrice());
        warehouse.dispatch(customer, item);
    }
}
