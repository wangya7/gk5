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
                String element = queue.take();
                System.out.println(Thread.currentThread().getName() + "queue take " + element);
                Thread.sleep((int) (Math.random() * 50));
            }
        } catch (InterruptedException e) {
            //
        }
    }
}
