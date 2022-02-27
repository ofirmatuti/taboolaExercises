package exercise4;

import java.util.Random;

public class CacheService {

    ICache<Integer,Integer> cache;
    int capacity;

    public CacheService(int capacity) {
        this.capacity = capacity;
        cache = new LRUCache<Integer,Integer>(capacity);
    }

    public Integer getData(Integer element) throws InterruptedException {
        Integer res = cache.cacheGet(element);
        if(res == null){
            System.out.println("executing a network request to the database");
            Thread.sleep(200);
            res = new Random().nextInt(1000);
            setData(element, res);
        }
        return res;
    }

    private void setData(Integer key, Integer val){
        cache.cacheSet(key, val);
    }
}
