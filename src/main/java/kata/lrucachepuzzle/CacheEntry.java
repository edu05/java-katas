package kata.lrucachepuzzle;

public class CacheEntry {
    private final Object key;
    private final Object entry;
    private CacheEntry previous;
    private CacheEntry next;

    CacheEntry(Object key, Object entry, CacheEntry previous, CacheEntry next) {
        this.key = key;
        this.entry = entry;
        this.previous = previous;
        this.next = next;
    }

    public static CacheEntry newEntry(Object key, Object entry) {
        return new CacheEntry(key, entry, null, null);
    }

    public Object getEntry() {
        return entry;
    }

    public Object getKey() {
        return key;
    }

    public CacheEntry getPrevious() {
        return previous;
    }

    public CacheEntry getNext() {
        return next;
    }

    public void setPrevious(CacheEntry previous) {
        this.previous = previous;
    }

    public void setNext(CacheEntry next) {
        this.next = next;
    }
}
