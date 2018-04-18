package kata.amazon;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AmazonTest {

    @Test
    public void testBoughtItemsGetDispatchedToCustomer() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        RecordingWarehouse recordingWarehouse = new RecordingWarehouse();
        Amazon amazon = new Amazon(recordingWarehouse, new Wallet(), new Notifier());

        amazon.buy(customer, item);

        assertThat(recordingWarehouse.getRecordedDispatchedCustomer(), is(customer));
        assertThat(recordingWarehouse.getRecordedDispatchedItem(), is(item));
    }

    @Test
    public void testBoughtItemsGetDispatchedToCustomerImproved() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        Warehouse warehouse = mock(Warehouse.class);
        Amazon amazon = new Amazon(warehouse, new Wallet(), new Notifier());

        amazon.buy(customer, item);

        verify(warehouse).dispatch(customer, item);
    }

    @Test
    public void testPayForBoughtItem() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        RecordingWallet recordingWallet = new RecordingWallet();
        Amazon amazon = new Amazon(new Warehouse(), recordingWallet, new Notifier());

        amazon.buy(customer, item);

        assertThat(recordingWallet.getRecordedPayingCustomer(), is(customer));
        assertThat(recordingWallet.getRecordedPaidAmount(), is(10.0));
    }

    @Test
    public void testPayForBoughtItemImproved() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        Wallet wallet = mock(Wallet.class);
        Amazon amazon = new Amazon(new Warehouse(), wallet, new Notifier());

        amazon.buy(customer, item);

        verify(wallet).bill(customer, 10.0);
    }

    @Test
    public void testNotifiedAfterPurchase() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        RecordingNotifier recordingNotifier = new RecordingNotifier();
        Amazon amazon = new Amazon(new Warehouse(), new Wallet(), recordingNotifier);

        amazon.buy(customer, item);

        assertThat(recordingNotifier.getRecordedNotifiedMessage(), is("You have bought a toaster!"));
    }
}