package kata.amazon;

public class RecordingWallet extends Wallet {
    private Customer recordedPayingCustomer;
    private double recordedPaidAmount;

    @Override
    public void bill(Customer customer, double amount) {
        this.recordedPayingCustomer = customer;
        this.recordedPaidAmount = amount;
    }

    public Customer getRecordedPayingCustomer() {
        return recordedPayingCustomer;
    }

    public double getRecordedPaidAmount() {
        return recordedPaidAmount;
    }
}
