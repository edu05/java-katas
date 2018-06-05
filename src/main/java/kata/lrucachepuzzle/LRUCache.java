package kata.lrucachepuzzle;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int size;
    private final Map<Object, CacheEntry> cache = new HashMap<>();

    private CacheEntry first = null;
    private CacheEntry last = null;

    public LRUCache(int size) {
        this.size = size;
    }

    public void put(Object key, Object value) {
        CacheEntry newEntry = CacheEntry.newEntry(key, value);

        //if cache is empty
           //make first indicator point to newEntry
           //make last indicator point to newEntry


        //else if cache contains an item for key
           //don't need to evict oldest entry (just FYI, no code needed for this line)
           //remove existing entry from cache
           //remove existing entry from list
           //insert newEntry as first item in the list - template A


        //else if cache is full
           //remove oldest entry from cache
           //remove oldest entry from list
           //insert newEntry as first item in the list - template A


        //else
           //insert newEntry as first item in the list - template A


        //insert newEntry into cache
    }

    private void removeFromList(CacheEntry cacheEntry) {
        //if cacheEntry is first
           //remove first entry - template B


        //else if cacheEntry is last
           //remove last entry - template C


        //else
           //remove cacheEntry from the middle - template D
    }

    public Object get(Object key) {
        CacheEntry cacheEntry = cache.get(key);
        if (cacheEntry == null) {
            return null;
        }

        moveToTheFront(cacheEntry);
        return cacheEntry.getEntry();
    }

    private void moveToTheFront(CacheEntry cacheEntry) {
        //if cache entry is first
           //don't need to do anything, cacheEntry is already the LRU item!


        //if cache entry is last
           //move cacheEntry to the front from the last position - template E


        //else
           //move cacheEntry to the front from the middle - template F
    }
}
