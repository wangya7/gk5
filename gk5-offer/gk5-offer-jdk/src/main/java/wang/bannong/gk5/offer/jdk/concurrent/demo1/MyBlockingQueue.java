package wang.bannong.gk5.offer.jdk.concurrent.demo1;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBlockingQueue<E> {

    private final Queue<E> queue;
    private final int      limit;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        this.queue = new ArrayDeque<>(limit);
    }

    public synchronized void put(E element) throws InterruptedException {
        while (limit == queue.size()) {
            wait();
        }

        queue.add(element);
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        E e = queue.poll();
        notify();
        return e;
    }
}
