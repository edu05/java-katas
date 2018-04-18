package kata.amazon;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AmazonTest {

    private Wallet wallet = mock(Wallet.class);

    @Before
    public void setUp() throws Exception {
        when(wallet.hasEnoughMoney(any(Customer.class), anyDouble())).thenReturn(true);
    }

    @Test
    public void testBoughtItemsGetDispatchedToCustomer() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        RecordingWarehouse recordingWarehouse = new RecordingWarehouse();
        Amazon amazon = new Amazon(recordingWarehouse, new InfiniteMoneyWallet(), new Notifier());

        amazon.buy(customer, item);

        assertThat(recordingWarehouse.getRecordedDispatchedCustomer(), is(customer));
        assertThat(recordingWarehouse.getRecordedDispatchedItem(), is(item));
    }

    @Test
    public void testBoughtItemsGetDispatchedToCustomerImproved() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        Warehouse warehouse =   mock(Warehouse.class);
        Amazon amazon = new Amazon(warehouse, wallet, new Notifier());

        amazon.buy(customer, item);

        verify(warehouse).dispatch(customer, item);
    }

    @Test
    public void testPayForBoughtItem() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        InfiniteMoneyWallet recordingWallet = new InfiniteMoneyWallet();
        Amazon amazon = new Amazon(new Warehouse(), recordingWallet, new Notifier());

        amazon.buy(customer, item);

        assertThat(recordingWallet.getRecordedPayingCustomer(), is(customer));
        assertThat(recordingWallet.getRecordedPaidAmount(), is(10.0));
    }

    @Test
    public void testPayForBoughtItemImproved() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        Amazon amazon = new Amazon(new Warehouse(), wallet, new Notifier());

        amazon.buy(customer, item);

        verify(wallet).bill(customer, 10.0);
    }

    @Test
    public void testNotifiedAfterPurchase() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        RecordingNotifier recordingNotifier = new RecordingNotifier();
        Amazon amazon = new Amazon(new Warehouse(), new InfiniteMoneyWallet(), recordingNotifier);

        amazon.buy(customer, item);

        assertThat(recordingNotifier.getRecordedNotifiedMessage(), is("You have bought a toaster!"));
    }

    @Test
    public void testNotifiedAfterPurchaseImproved() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        Notifier recordingNotifier = mock(Notifier.class);
        Amazon amazon = new Amazon(new Warehouse(), wallet, recordingNotifier);

        amazon.buy(customer, item);

        verify(recordingNotifier).notify("You have bought a toaster!");
    }

    @Test
    public void testNotEnoughMoney() throws Exception {
        Item item = new Item(10, "toaster");
        Customer customer = new Customer();
        RecordingNotifier recordingNotifier = new RecordingNotifier();
        RecordingWarehouse recordingWarehouse = new RecordingWarehouse();
        NoMoneyWallet noMoneyWallet = new NoMoneyWallet();
        Amazon amazon = new Amazon(recordingWarehouse, noMoneyWallet, recordingNotifier);

        try {
            amazon.buy(customer, item);
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