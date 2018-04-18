package kata.amazon;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class AmazonTest {

    private static final Customer CUSTOMER = new Customer();
    private static final Item ITEM = new Item(10, "toaster");

    @Test
    public void testBoughtItemsGetDispatchedToCustomer() throws Exception {
        RecordingWarehouse recordingWarehouse = new RecordingWarehouse();
        Amazon amazon = new Amazon(recordingWarehouse, new InfiniteMoneyWallet(), new Notifier());

        amazon.buy(CUSTOMER, ITEM);

        assertThat(recordingWarehouse.getRecordedDispatchedCustomer(), is(CUSTOMER));
        assertThat(recordingWarehouse.getRecordedDispatchedItem(), is(ITEM));
    }

    @Test
    public void testPayForBoughtItem() throws Exception {
        InfiniteMoneyWallet recordingWallet = new InfiniteMoneyWallet();
        Amazon amazon = new Amazon(new Warehouse(), recordingWallet, new Notifier());

        amazon.buy(CUSTOMER, ITEM);

        assertThat(recordingWallet.getRecordedPayingCustomer(), is(CUSTOMER));
        assertThat(recordingWallet.getRecordedPaidAmount(), is(10.0));
    }

    @Test
    public void testNotifiedAfterPurchase() throws Exception {
        RecordingNotifier recordingNotifier = new RecordingNotifier();
        Amazon amazon = new Amazon(new Warehouse(), new InfiniteMoneyWallet(), recordingNotifier);

        amazon.buy(CUSTOMER, ITEM);

        assertThat(recordingNotifier.getRecordedNotifiedMessage(), is("You have bought a toaster!"));
    }

    @Test
    public void testNotEnoughMoney() throws Exception {
        RecordingNotifier recordingNotifier = new RecordingNotifier();
        RecordingWarehouse recordingWarehouse = new RecordingWarehouse();
        NoMoneyWallet noMoneyWallet = new NoMoneyWallet();
        Amazon amazon = new Amazon(recordingWarehouse, noMoneyWallet, recordingNotifier);

        try {
            amazon.buy(CUSTOMER, ITEM);
            fail();
        } catch (NotEnoughMoneyException e) {
        }

        assertNull(recordingWarehouse.getRecordedDispatchedItem());
        assertNull(recordingWarehouse.getRecordedDispatchedCustomer());
        assertNull(recordingNotifier.getRecordedNotifiedMessage());
        assertNull(noMoneyWallet.getRecordedPayingCustomer());
        assertNull(noMoneyWallet.getRecordedPaidAmount());
    }
}