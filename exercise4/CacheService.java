package exercise4;

import java.util.Random;

public class CacheService extends LRUCache<Integer,Integer> {

    public CacheService(int capacity) {
        super(capacity);
    }

    public Integer getData(Integer element) throws InterruptedException {
        Integer res = cacheGet(element);
        if(res == null){
            System.out.println("executing a network request to the database");
            Thread.sleep(200);
            res = new Random().nextInt(1000);
            setData(element, res);
        }
        return res;
    }

    private void setData(Integer key, Integer val){
        cacheSet(key, val);
    }
}
