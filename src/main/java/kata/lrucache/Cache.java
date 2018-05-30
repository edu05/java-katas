package kata.lrucache;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private final Map<Object, Object> cache = new HashMap<>();

    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    public Object get(Object key) {
        return cache.get(key);
    }
}
