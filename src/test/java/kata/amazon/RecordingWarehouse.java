package kata.amazon;

public class RecordingWarehouse extends Warehouse {


    private Customer recordedDispatchedCustomer;
    private Item recordedDispatchedItem;

    @Override
    public void dispatch(Customer customer, Item item) {
        this.recordedDispatchedCustomer = customer;
        this.recordedDispatchedItem = item;
    }

    public Customer getRecordedDispatchedCustomer() {
        return recordedDispatchedCustomer;
    }

    public Item getRecordedDispatchedItem() {
        return recordedDispatchedItem;
    }
}
