package exercise4;

import java.util.LinkedHashMap;
import java.util.Map;
/*
    not a thread safe solution
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> implements ICache<Integer,Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    @Override
    public Integer cacheGet(Integer key) {
        return getOrDefault(key, -1);
    }

    @Override
    public void cacheSet(Integer key, Integer value) {
        put(key, value); // invokes removeEldestEntry
    }
}