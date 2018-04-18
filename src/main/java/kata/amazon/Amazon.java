package kata.amazon;

public class Amazon {


    private final Warehouse warehouse;
    private final Wallet wallet;
    private final Notifier notifier;

    public Amazon(Warehouse warehouse, Wallet wallet, Notifier notifier) {
        this.warehouse = warehouse;
        this.wallet = wallet;
        this.notifier = notifier;
    }

    public void buy(Customer customer, Item item) {
        wallet.bill(customer, item.getPrice());
        warehouse.dispatch(customer, item);
        notifier.notify("You have bought a " + item.getDescription() + "!");
    }
}
