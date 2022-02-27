package exercise4;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> implements ICache<K,V> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    @Override
    public V cacheGet(K key) {
        return getOrDefault(key, null);
    }

    @Override
    public void cacheSet(K key, V value) {
        put(key, value);
    }
}