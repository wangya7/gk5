package wang.bannong.gk5.offer.jdk.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();
    protected final Condition notEmpty = lock.newCondition();
    protected final Condition notFull = lock.newCondition();
    protected final T[] buff = (T[]) new Object[1024];
    private int head, tail, count;

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == buff.length) {
                notFull.await();
            }
            buff[tail] = t;
            if (++tail == buff.length) {
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.wait();
            }
            T t = buff[head];
            buff[head] = null;
            if (++head == buff.length) {
                head = 0;
            }
            --count;
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
}
