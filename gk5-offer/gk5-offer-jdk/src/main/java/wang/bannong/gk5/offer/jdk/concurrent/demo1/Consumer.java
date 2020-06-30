package wang.bannong.gk5.offer.jdk.concurrent.demo1;

public class Consumer implements Runnable {

    private final MyBlockingQueue<String> queue;

    public Consumer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {

                String element = queue.poll();
                System.out.println(Thread.currentThread().getName() + "queue poll " + element);
                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
            //
        }
    }
}
