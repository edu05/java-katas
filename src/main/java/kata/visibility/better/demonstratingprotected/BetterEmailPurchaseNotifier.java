package kata.visibility.better.demonstratingprotected;

public class BetterEmailPurchaseNotifier extends BetterPurchaseNotifier {

    private final String emailAddress;

    public BetterEmailPurchaseNotifier(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    protected void send(String message) {
        System.out.println("Sending following email to " + emailAddress + ":\n" + message);
    }
}
