package wang.bannong.gk5.offer.jdk.concurrent.demo2;

/**
 * 项目上线 大伙都在加班 只有大家全部完成各自工作再一起下班
 */
public class GoHomeAtSameTime implements Runnable {

    private final JobWorkerNum workerNum;

    public GoHomeAtSameTime(JobWorkerNum workerNum) {
        this.workerNum = workerNum;
    }

    @Override
    public void run() {
        try {
            // simulate working on task
            Thread.sleep((int) (Math.random() * 1000));
            workerNum.countDown();
            System.out.println( Thread.currentThread().getName() +" has finished work");
        } catch (InterruptedException e) {

        }
    }

    static class JobWorkerNum {

        private int num;

        public JobWorkerNum(int num) {
            this.num = num;
        }

        public synchronized void await() throws InterruptedException {
            // 只要还有一个人未完成任务 那么就继续等
            while (num > 0) {
                wait();
            }
        }

        public synchronized void countDown() {
            num--;
            if (num == 0) {
                notifyAll();
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        int runnerTotal = 10;
        JobWorkerNum workerNum = new JobWorkerNum(runnerTotal);

        for (int i = 0; i < runnerTotal; i++) {
            new Thread(new GoHomeAtSameTime(workerNum)).start();
        }
        workerNum.await();
        System.out.println("collect worker results");
    }
}
