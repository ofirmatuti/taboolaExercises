package exercise4;

public class CacheService {

    ICache<Integer,String> cache;
    int capacity;

    public CacheService(int capacity) {
        this.capacity = capacity;
        cache = new LRUCache<Integer,String>(capacity);
    }

    public String getData(Integer key) throws InterruptedException {
        String data = cache.cacheGet(key);
        if(data == null){
            System.out.println("executing a network request to the database");
            Thread.sleep(200);
            data = "some record from the database";
            setData(key, data);
        }
        return data;
    }

    private void setData(Integer key, String val){
        cache.cacheSet(key, val);
    }
}
