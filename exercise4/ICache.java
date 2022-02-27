package exercise4;

public interface ICache<K,V> {
    V cacheGet(K key);
    void cacheSet(K key, V value);
}
