package wang.bannong.gk5.offer.jdk.concurrent.cases;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap实现
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private static final long serialVersionUID = 8083179879983758378L;

    private static final int MAX_ENTRIES = 5;

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }


    public static void main(String[] args) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("Tom", 31);
        map.put("Jerry", 35);
        map.put("Pro", 33);
        map.put("Tony", 26);
        map.put("Joke", 34);
        map.put("San", 11);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println(map.get("Tom"));
        System.out.println(map.get("Jerry"));
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
