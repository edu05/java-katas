package kata.lrucache;

import java.util.HashMap;
import java.util.Map;

import static kata.lrucache.CacheEntry.newEntry;

public class LRUCache {

    private final Map<Object, CacheEntry> cache = new HashMap<>();
    private final int cacheSize;

    public LRUCache(int cacheSize) {
        if (cacheSize < 1) {
            throw new IllegalArgumentException("Well what's the point of an empty cache?");
        }
        this.cacheSize = cacheSize;
    }

    public void put(Object key, Object value) {
        if (!cache.containsKey(key) && cache.size() == cacheSize) {
            CacheEntry mostUnusedEntry = cache.values().stream().findFirst().get();
            for (CacheEntry nextCacheEntry : cache.values()) {
                if (nextCacheEntry.getLastAccessTime().isBefore(mostUnusedEntry.getLastAccessTime())) {
                    mostUnusedEntry = nextCacheEntry;
                }
            }
            cache.remove(mostUnusedEntry.getKey());
        }
        cache.put(key, newEntry(key, value));
    }

    public Object get(Object key) {
        return cache.getOrDefault(key, newEntry(key, null)).getEntry();
    }
}
