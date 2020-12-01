package wang.bannong.gk5.offer.jdk;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceQueueTest {

    private static ReferenceQueue<byte[]> rq  = new ReferenceQueue<>();
    private static int                    _1M = 1024 * 1024;

    @Test
    public void demo1() {
        Object value = new Object();
        Map<WeakReference<byte[]>, Object> map = new HashMap<>();

        Thread thread = new Thread(() -> {
            try {
                int n = 0;
                WeakReference k;
                while ((k = (WeakReference) rq.remove()) != null) {
                    System.out.println((++n) + "回收了:" + k);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 100; i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes, rq);
            map.put(weakReference, value);
        }
        System.out.println("map.size->" + map.size());

        int aliveNum = 0;
        for (Map.Entry<WeakReference<byte[]>, Object> entry : map.entrySet()) {
            if (entry != null) {
                if (entry.getKey().get() != null) {
                    aliveNum++;
                }
            }
        }
        System.out.println("100个对象中存活的对象数量：" + aliveNum);
    }

    private static ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();

    @Test
    public void demo2() {
        final Map<Object, MyWeakReference> hashMap = new HashMap<>();
        Thread thread = new Thread(() -> {
            try {
                int n = 0;
                MyWeakReference k;
                while (null != (k = (MyWeakReference) referenceQueue.remove())) {
                    System.out.println((++n) + "回收了:" + k);
                    // 反向获取，移除对应的entry
                    hashMap.remove(k.key);

                    // 额外对key对象作其它处理，比如关闭流，通知操作等
                    // ....
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            byte[] bytesKey = new byte[_1M];
            byte[] bytesValue = new byte[_1M];
            hashMap.put(bytesKey, new MyWeakReference(bytesKey, bytesValue, referenceQueue));
        }
    }

    static class MyWeakReference extends WeakReference<byte[]> {
        private Object key;

        MyWeakReference(Object key, byte[] referent, ReferenceQueue<? super byte[]> q) {
            super(referent, q);
            this.key = key;
        }
    }


    @Test
    public void demo3() throws InterruptedException {
        Map<Object, MyPhantomReference> map = new HashMap<>();

        Thread thread = new Thread(() -> {
            try {
                int n = 0;
                MyPhantomReference k;
                while (null != (k = (MyPhantomReference) referenceQueue.remove())) {
                    System.out.println((++n) + "回收了:" + k);
                    // 反向获取，移除对应的entry
                    map.remove(k.key);

                    // 额外对key对象作其它处理，比如关闭流，通知操作等
                    // ....
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            byte[] bytesKey = new byte[_1M];
            byte[] bytesValue = new byte[_1M];
            map.put(bytesKey, new MyPhantomReference(bytesKey, bytesValue, referenceQueue));
        }
    }

    static class MyPhantomReference extends PhantomReference<byte[]> {
        private Object key;

        MyPhantomReference(Object key, byte[] referent, ReferenceQueue<? super byte[]> q) {
            super(referent, q);
            this.key = key;
        }
    }
}
