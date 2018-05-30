package kata.lrucache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class LRUCacheTest {

    @Test
    public void shouldRetrieveNullForUnknownKeys() throws Exception {
        LRUCache lruCache = new LRUCache(3);
        assertNull(lruCache.get("key"));
    }

    @Test
    public void shouldStoreAndRetrieveItems() throws Exception {

        LRUCache LRUCache = new LRUCache(3);

        LRUCache.put("key", "value");
        assertThat(LRUCache.get("key"), is("value"));
    }

    @Test
    public void shouldBeAbleToInsertMoreThanNItems() throws Exception {
        LRUCache lruCache = new LRUCache(3);

        lruCache.put("key", "value");
        lruCache.put("house keys", "house");
        lruCache.put("car keys", "car");
        lruCache.get("key");
        lruCache.put("yacht keys", "yacht");

        assertThat(lruCache.get("key"), is("value"));
        assertThat(lruCache.get("car keys"), is("car"));
        assertThat(lruCache.get("yacht keys"), is("yacht"));
        assertNull(lruCache.get("house keys"));
    }

    @Test
    public void shouldBeAbleToUpdateItemAfterCacheIsFull() throws Exception {
        LRUCache lruCache = new LRUCache(3);

        lruCache.put("key", "value");
        lruCache.put("house keys", "house");
        lruCache.put("car keys", "car");
        lruCache.put("house keys", "mansion");

        assertThat(lruCache.get("key"), is("value"));
        assertThat(lruCache.get("house keys"), is("mansion"));
        assertThat(lruCache.get("car keys"), is("car"));
    }
}