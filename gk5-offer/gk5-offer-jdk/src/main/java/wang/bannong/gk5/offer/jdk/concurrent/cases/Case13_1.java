package wang.bannong.gk5.offer.jdk.concurrent.cases;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实现Map
 */
public final class Case13_1 {

    // 毫无意思的测试
    public static void main(String[] args) {
        Map<String, Long> mapNoConc = new HashMap<>();
        ReadWriteMap map = new ReadWriteMap(mapNoConc);
        int threadSize = 20;
        Thread[] putthreads = new Thread[threadSize];
        Thread[] getthreads = new Thread[threadSize];

        Random random = new Random();
        for (int i = 0; i < threadSize; ++i) {
            final String k = "" + i;
            putthreads[i] = new Thread(() -> map.put(k, random.nextLong()));
        }

        for (int i = 0; i < threadSize; ++i) {
            final String k = "" + i;
            getthreads[i] = new Thread(() -> System.out.println(Thread.currentThread().getName() + ": " + k + "/" + map.get(k)));
        }

        for (int i = 0; i < threadSize; ++i) {
            putthreads[i].start();
            getthreads[i].start();
        }

    }

}


/**
 * ConcurrentHashMap可以满足大部分并发场景，如果需要更好性能，可以基于读写锁实现
 */
final class ReadWriteMap<K, V> {

    private       Map<K, V>              map;
    private final ReentrantReadWriteLock lock      = new ReentrantReadWriteLock();
    private final Lock                   readLock  = lock.readLock();
    private final Lock                   writeLock = lock.writeLock();

    public ReadWriteMap(Map<K, V> map) {
        this.map = map;
    }

    public final V put(K k, V v) {
        writeLock.lock();
        try {
            System.out.println("put entry,k:" + k + ", v:" + v);
            return map.put(k, v);
        } finally {
            writeLock.unlock();
        }
    }

    public final V get(K k) {
        readLock.lock();
        try {
            return map.get(k);
        } finally {
            readLock.unlock();
        }
    }
}