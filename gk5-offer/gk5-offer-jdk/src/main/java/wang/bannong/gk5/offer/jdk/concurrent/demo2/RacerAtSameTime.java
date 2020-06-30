package wang.bannong.gk5.offer.jdk.concurrent.demo2;

/**
 * 同时开始
 */
public class RacerAtSameTime implements Runnable {

    private final FireFlag fireFlag;

    public RacerAtSameTime(FireFlag fireFlag) {
        this.fireFlag = fireFlag;
    }

    @Override
    public void run() {
        try {
            fireFlag.ready();
            System.out.println("Start run " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class FireFlag {
        private volatile boolean fire = false;

        public synchronized void ready() throws InterruptedException {
            while (!fire) {
                wait();
            }
        }

        public synchronized void go() {
            fire = true;
            notifyAll();
        }
    }

    public static void main(String...args) throws InterruptedException {
        FireFlag fireFlag = new FireFlag();
        int runnerTotal = 10;
        for (int i = 0; i < runnerTotal; i++) {
            new Thread(new RacerAtSameTime(fireFlag)).start();
        }
        Thread.sleep(3000);
        System.out.println("=======");
        fireFlag.go();
    }
}
