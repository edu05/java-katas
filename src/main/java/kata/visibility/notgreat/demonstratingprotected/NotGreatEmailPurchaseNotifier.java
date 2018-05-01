package kata.visibility.notgreat.demonstratingprotected;

public class NotGreatEmailPurchaseNotifier implements NotGreatPurchaseNotifier {

    private final String emailAddress;

    public NotGreatEmailPurchaseNotifier(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void notify(String customer, String item) {
        String message = new StringBuilder()
                .append("Sending following email to " + emailAddress + ":\n")
                .append("Dear ").append(customer).append(":\n")
                .append("Thanks for buying ").append(item).append(" with us.\n")
                .append("Please write a review for ").append(item).append(" to opt in for a free Yatch!")
                .toString();

        System.out.println(message);
    }
}
