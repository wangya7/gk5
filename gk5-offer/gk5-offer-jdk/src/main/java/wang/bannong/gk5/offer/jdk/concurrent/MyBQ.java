package wang.bannong.gk5.offer.jdk.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBQ<E> {

    private Queue<E> queue;
    private int maxSize;

    public MyBQ(int maxSize) {
        maxSize = maxSize;
        queue = new ArrayDeque<>(maxSize);
    }

    public synchronized void put(E e) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        queue.add(e);
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        E e = queue.poll();
        notify();
        return e;
    }

}
