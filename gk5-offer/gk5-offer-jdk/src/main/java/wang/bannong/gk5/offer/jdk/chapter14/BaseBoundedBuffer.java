package wang.bannong.gk5.offer.jdk.chapter14;

public class BaseBoundedBuffer<V> {
    private final V[] buf;
    private       int tail;   // 尾部即将插入的位置
    private       int head;   // 头部
    private       int count;  // 缓存元素个数

    protected BaseBoundedBuffer(int capacity) {
        buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length) {
            tail = 0;
        }
        ++count;
    }

    protected synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        --count;
        return v;
    }

    protected synchronized final boolean isFull() {
        return count == buf.length;
    }

    protected synchronized final boolean isEmpty() {
        return count == 0;
    }
}
