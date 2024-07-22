package wang.bannong.gk5.offer.jdk.concurrent.demo1;

import java.util.concurrent.CountDownLatch;

public class ProducerAndConsumerClient {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        Producer p1 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        new Thread(p1).start();
        new Thread(c1).start();
        new Thread(c2).start();
        new CountDownLatch(1).await();
    }
}
