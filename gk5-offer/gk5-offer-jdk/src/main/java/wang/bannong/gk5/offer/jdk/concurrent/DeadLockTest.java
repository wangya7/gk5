package wang.bannong.gk5.offer.jdk.concurrent;

import java.util.Objects;

public class DeadLockTest implements Runnable {

    private static Object src1 = new Object();
    private static Object src2 = new Object();

    private final boolean flag;

    DeadLockTest(boolean flag) {
        this.flag = flag;
    }


    @Override
    public void run() {
        if (flag) {
            synchronized (src1) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Get src1");
                    Thread.sleep(2000);
                    synchronized (src2) {
                        System.out.println(Thread.currentThread().getName() + " Get src2");
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {

                }
            }
        } else {
            synchronized (src2) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Get src2");
                    Thread.sleep(2000);
                    synchronized (src1) {
                        System.out.println(Thread.currentThread().getName() + " Get src1");
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new DeadLockTest(false), "T-1").start();
        new Thread(new DeadLockTest(true), "T-2").start();
    }
}
