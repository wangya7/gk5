package wang.bannong.gk5.offer.jdk.concurrent;

import org.junit.Test;

public class InterruptDemo {

    @Test
    public void demo1() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
    }
}
