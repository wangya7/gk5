package wang.bannong.gk5.offer.jdk.chapter14;

public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    protected SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    break;
                }
            }
            Thread.sleep(3000);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(3000);
        }
    }

}
