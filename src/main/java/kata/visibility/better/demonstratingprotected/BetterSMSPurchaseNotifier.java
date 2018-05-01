package kata.visibility.better.demonstratingprotected;

public class BetterSMSPurchaseNotifier extends BetterPurchaseNotifier {

    private final String phoneNumber;

    public BetterSMSPurchaseNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void send(String message) {
        System.out.println("Sending following SMS to " + phoneNumber + ":\n" + message);
    }
}
