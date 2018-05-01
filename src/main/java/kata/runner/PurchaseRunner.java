package kata.runner;

import kata.visibility.better.demonstratingprotected.BetterEmailPurchaseNotifier;
import kata.visibility.better.demonstratingprotected.BetterSMSPurchaseNotifier;
import kata.visibility.notgreat.demonstratingprotected.NotGreatEmailPurchaseNotifier;
import kata.visibility.notgreat.demonstratingprotected.NotGreatSMSPurchaseNotifier;

public class PurchaseRunner {

    public static void main(String[] args) {
        System.out.println("----not great----");
        System.out.println("--sms--");
        new NotGreatSMSPurchaseNotifier("07790").notify("edu", "a shiny new phone");
        System.out.println("--email--");
        new NotGreatEmailPurchaseNotifier("edu@ig.com").notify("edu", "a shiny new phone");
        System.out.println("----better----");
        System.out.println("--sms--");
        new BetterSMSPurchaseNotifier("07790").notify("edu", "a shiny new phone");
        System.out.println("--email--");
        new BetterEmailPurchaseNotifier("edu@ig.com").notify("edu", "a shiny new phone");
    }
}
