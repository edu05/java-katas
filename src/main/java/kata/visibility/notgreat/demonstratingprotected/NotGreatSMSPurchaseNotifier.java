package kata.visibility.notgreat.demonstratingprotected;

public class NotGreatSMSPurchaseNotifier implements NotGreatPurchaseNotifier {

    private final String phoneNumber;

    public NotGreatSMSPurchaseNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String customer, String item) {
        String message = new StringBuilder()
                .append("Sending following SMS to " + phoneNumber + ":\n")
                .append("Dear ").append(customer).append(":\n")
                .append("Thanks for buying ").append(item).append(" with us.\n")
                .append("Please write a review for ").append(item).append(" to opt in for a free Yatch!")
                .toString();

        System.out.println(message);
    }
}
