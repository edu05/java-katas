package kata.lrucache;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private final Map<Object, Object> cache = new HashMap<>();
    private final int cacheSize;

    public Cache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void put(Object key, Object value) {
        if (cache.containsKey(key) || cache.size() < cacheSize) {
            cache.put(key, value);
        }
    }

    public Object get(Object key) {
        return cache.get(key);
    }
}
