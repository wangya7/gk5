package wang.bannong.gk5.offer.jdk.concurrent.demo1;

public class Producer implements Runnable {

    private final MyBlockingQueue<String> queue;

    public Producer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int num = 0;
            while (true) {
                String element = Thread.currentThread().getName() + ":" + String.valueOf(num++);
                queue.add(element);
                System.out.println("queue add " + element);
                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
            //
        }
    }
}
