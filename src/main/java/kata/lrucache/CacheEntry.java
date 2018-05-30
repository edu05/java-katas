package kata.lrucache;

import java.time.LocalDateTime;

public class CacheEntry {

    private final Object key;
    private final Object entry;
    private LocalDateTime lastAccessTime;

    private CacheEntry(Object key, Object entry, LocalDateTime lastAccessTime) {
        this.key = key;
        this.entry = entry;
        this.lastAccessTime = lastAccessTime;
    }

    public static CacheEntry newEntry(Object key, Object entry) {
        return new CacheEntry(key, entry, LocalDateTime.now());
    }

    public Object getEntry() {
        lastAccessTime = LocalDateTime.now();
        return entry;
    }

    public Object getKey() {
        return key;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }
}
