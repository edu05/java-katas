package kata.visibility.better.demonstratingprotected;

/**
 * Without the protected method send, every class implementing (or extending) the BetterPurchaseNotifier would need to have the
 * code for building the purchase message inside it, this would lead to:
 * 1. duplicated code
 * 2. having to update multiple classes each time we wanted to update the purchase message
 * 3. if you forget to update all classes inheriting from the BetterPurchaseNotifier you risk sending different messages to different customers!
 */
public abstract class BetterPurchaseNotifier {



    public void notify(String customer, String item) {
        String message = new StringBuilder()
                .append("Dear ").append(customer).append(":\n")
                .append("Thanks for buying ").append(item).append(" with us.\n")
                .append("Please write a review for ").append(item).append(" to opt in for a free Yatch!")
                .toString();

        send(message);
    }

    protected abstract void send(String message);




}
