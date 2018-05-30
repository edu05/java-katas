package kata.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private CacheEntry first = null;
    private CacheEntry last = null;
    private final Map<Object, CacheEntry> cache = new HashMap<>();
    private final int cacheSize;

    public LRUCache(int cacheSize) {
        if (cacheSize < 1) {
            throw new IllegalArgumentException("Well what's the point of an empty cache?");
        }
        this.cacheSize = cacheSize;
    }

    public void put(Object key, Object value) {
        CacheEntry newEntry = CacheEntry.newEntry(key, value);
        if (last == null) { //if the cache is empty
            first = newEntry;
            last = newEntry;
        } else { //the cache isn't empty, i.e. first and last values have been set
            CacheEntry existingCacheEntry = cache.get(key);
            if (existingCacheEntry != null) { //if there's already an entry remove it, we'll reinsert it soon
                remove(existingCacheEntry);
            } else if (cache.size() == cacheSize) {//if the cache is full and a new entry is going in we need to remove the most unused entry
                cache.remove(last.getKey());
                remove(last);
            }

            //make the first item in the list
            first.setPrevious(newEntry);
            newEntry.setNext(first);
            first = newEntry;
        }

        cache.put(key, newEntry);
    }

    public Object get(Object key) {

        CacheEntry cacheEntry = cache.get(key);
        if (cacheEntry == null) {
            return null;
        }

        placeFirst(cacheEntry);
        return cacheEntry.getEntry();
    }

    private void placeFirst(CacheEntry cacheEntry) {
        if (cacheEntry == first) { //if the item is already the first don't do anything
            return;
        }

        if (cacheEntry == last) { //if it's the last item update the last indicator
            last.getPrevious().setNext(null);
            last = last.getPrevious();
        } else { //if it's not the last item re-link the previous and next entries
            CacheEntry previous = cacheEntry.getPrevious();
            CacheEntry next = cacheEntry.getNext();
            previous.setNext(next);
            next.setPrevious(previous);
        }

        //make it the first entry
        first.setPrevious(cacheEntry);
        cacheEntry.setNext(first);
        cacheEntry.setPrevious(null);
        first = cacheEntry;
    }

    private void remove(CacheEntry cacheEntry) {
        if (cacheEntry == first) { //if first need to update the next entry and the first indicator
            first.getNext().setPrevious(null);
            first = first.getNext();
            cacheEntry.setNext(null);
        } else if (cacheEntry == last) { //if last need to update the previous entry and the last indicator
            last.getPrevious().setNext(null);
            last = last.getPrevious();
            cacheEntry.setPrevious(null);
        } else { //otherwise need to re-link previous and next
            CacheEntry previous = cacheEntry.getPrevious();
            CacheEntry next = cacheEntry.getNext();
            previous.setNext(next);
            next.setPrevious(previous);
            cacheEntry.setPrevious(null);
            cacheEntry.setNext(null);
        }
    }
}
