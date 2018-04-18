package kata.amazon;

public class InfiniteMoneyWallet extends Wallet {

    private Customer recordedPayingCustomer;
    private Double recordedPaidAmount;

    @Override
    public boolean hasEnoughMoney(Customer customer, double amount) {
        return true;
    }

    @Override
    public void bill(Customer customer, double amount) {
        this.recordedPaidAmount = amount;
        this.recordedPayingCustomer = customer;
    }

    public Customer getRecordedPayingCustomer() {
        return recordedPayingCustomer;
    }

    public Double getRecordedPaidAmount() {
        return recordedPaidAmount;
    }
}
