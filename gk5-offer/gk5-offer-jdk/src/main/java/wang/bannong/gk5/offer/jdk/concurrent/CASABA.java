package wang.bannong.gk5.offer.jdk.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASABA {

    private static AtomicInteger          count  = new AtomicInteger(10);
    private static AtomicStampedReference countS = new AtomicStampedReference(10, 0);

    @Test
    public void aba() {
        Thread one = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", count=" + count.get());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count.compareAndSet(10, 30);
            System.out.println(Thread.currentThread().getName() + ", count=" + count.get());
        }, "ONE");

        Thread two = new Thread(() -> {
            final int count1 = count.get();
            System.out.println(Thread.currentThread().getName() + ", count=" + count.get());
            count.compareAndSet(10, 45);
            System.out.println(Thread.currentThread().getName() + ", count=" + count.get());
            count.compareAndSet(45, 10);
            System.out.println(Thread.currentThread().getName() + ", count=" + count.get());
        }, "TWO");

        one.start();
        two.start();
    }

    @Test
    public void abaHandleup() throws InterruptedException {
        Thread one = new Thread(() -> {
            int stamp = countS.getStamp();
            System.out.println(Thread.currentThread().getName() + ", r=" + countS.getReference() + ", s=" + countS.getStamp());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean s = countS.compareAndSet(10, 30, stamp, stamp + 1);
            System.out.println(s);
            System.out.println(Thread.currentThread().getName() + ", r=" + countS.getReference() + ", s=" + countS.getStamp());
        }, "ONE");

        Thread two = new Thread(() -> {
            final int count1 = count.get();
            System.out.println(Thread.currentThread().getName() + ", r=" + countS.getReference() + ", s=" + countS.getStamp());
            countS.compareAndSet(10, 45, countS.getStamp(), countS.getStamp() + 3);
            System.out.println(Thread.currentThread().getName() + ", r=" + countS.getReference() + ", s=" + countS.getStamp());
            countS.compareAndSet(45, 10, countS.getStamp(), countS.getStamp() + 3);
            System.out.println(Thread.currentThread().getName() + ", r=" + countS.getReference() + ", s=" + countS.getStamp());
        }, "TWO");

        one.start();
        two.start();
        one.join();
        two.join();
    }
}
