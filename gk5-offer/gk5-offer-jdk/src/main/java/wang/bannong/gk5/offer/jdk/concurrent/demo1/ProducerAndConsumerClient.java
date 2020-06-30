package wang.bannong.gk5.offer.jdk.concurrent.demo1;

public class ProducerAndConsumerClient {
    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(queue)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(queue)).start();
        }
    }
}
