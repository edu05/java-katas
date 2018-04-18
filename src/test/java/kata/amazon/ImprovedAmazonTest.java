package kata.amazon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ImprovedAmazonTest {

    private static final Customer CUSTOMER = new Customer();
    private static final Item ITEM = new Item(10, "toaster");
    private Wallet wallet = mock(Wallet.class);
    private Warehouse warehouse = mock(Warehouse.class);
    private Notifier notifier = mock(Notifier.class);
    private Amazon amazon = new Amazon(warehouse, wallet, notifier);

    @Before
    public void setUp() throws Exception {
        when(wallet.hasEnoughMoney(any(Customer.class), anyDouble())).thenReturn(true);
    }


    @Test
    public void testBoughtItemsGetDispatchedToCustomerImproved() throws Exception {
        amazon.buy(CUSTOMER, ITEM);

        verify(warehouse).dispatch(CUSTOMER, ITEM);
    }


    @Test
    public void testPayForBoughtItemImproved() throws Exception {
        amazon.buy(CUSTOMER, ITEM);

        verify(wallet).bill(CUSTOMER, 10.0);
    }

    @Test
    public void testNotifiedAfterPurchaseImproved() throws Exception {
        amazon.buy(CUSTOMER, ITEM);

        verify(notifier).notify("You have bought a toaster!");
    }

    @Test
    public void testNotEnoughMoneyImproved() throws Exception {
        when(wallet.hasEnoughMoney(any(Customer.class), anyDouble())).thenReturn(false);
        try {
            amazon.buy(CUSTOMER, ITEM);
            fail();
        } catch (NotEnoughMoneyException e) {
        }

        verify(wallet, never()).bill(any(Customer.class), anyDouble());
        verifyZeroInteractions(warehouse, notifier);
    }
}