package wang.bannong.gk5.offer.jdk.chapter14;

import wang.bannong.gk5.offer.jdk.chapter14.exception.BufferEmptyException;
import wang.bannong.gk5.offer.jdk.chapter14.exception.BufferFullException;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    protected GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) {
        if (isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }

    public synchronized V take() {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        return doTake();
    }

}
