package wang.bannong.gk5.offer.jdk.concurrent.cases;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap实现
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private static final long serialVersionUID = 8083179879983758378L;

    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(cacheSize, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > this.cacheSize;
    }


    public static void main(String[] args) {
        Map<String, Integer> map = new LRUCache<>(3);
        map.put("Tom", 31);
        map.put("Jerry", 35);
        map.put("Pro", 33);
        map.put("Tony", 26);
        System.out.println(map);
        System.out.println(map.get("Tom"));
        System.out.println(map.get("Pro"));
        map.put("Tom", 31);
        System.out.println(map);
    }
}
