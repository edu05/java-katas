package kata.lrucache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CacheTest {

    @Test
    public void shouldRetrieveNullForUnknownKeys() throws Exception {
        Cache cache = new Cache(3);
        assertNull(cache.get("key"));
    }

    @Test
    public void shouldStoreAndRetrieveItems() throws Exception {

        Cache cache = new Cache(3);

        cache.put("key", "value");
        assertThat(cache.get("key"), is("value"));
    }

    @Test
    public void shouldNotBeAbleToInsertMoreThanNItems() throws Exception {
        Cache cache = new Cache(3);

        cache.put("key", "value");
        cache.put("house keys", "house");
        cache.put("car keys", "car");
        cache.put("yacht keys", "yacht");

        assertThat(cache.get("key"), is("value"));
        assertThat(cache.get("house keys"), is("house"));
        assertThat(cache.get("car keys"), is("car"));
        assertNull(cache.get("yacht keys"));
    }

    @Test
    public void shouldBeAbleToUpdateItemAfterCacheIsFull() throws Exception {
        Cache cache = new Cache(3);

        cache.put("key", "value");
        cache.put("house keys", "house");
        cache.put("car keys", "car");
        cache.put("house keys", "mansion");

        assertThat(cache.get("key"), is("value"));
        assertThat(cache.get("house keys"), is("mansion"));
        assertThat(cache.get("car keys"), is("car"));
    }
}