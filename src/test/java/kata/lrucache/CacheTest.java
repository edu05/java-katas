package kata.lrucache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CacheTest {

    @Test
    public void shouldRetrieveNullForUnknownKeys() throws Exception {
        Cache cache = new Cache();
        assertNull(cache.get("key"));
    }

    @Test
    public void shouldStoreAndRetrieveItems() throws Exception {

        Cache cache = new Cache();

        cache.put("key", "value");
        assertThat(cache.get("key"), is("value"));
    }
}