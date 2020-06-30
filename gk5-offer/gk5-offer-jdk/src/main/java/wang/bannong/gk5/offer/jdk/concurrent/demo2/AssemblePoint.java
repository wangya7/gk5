package wang.bannong.gk5.offer.jdk.concurrent.demo2;

/**
 * 集合点
 */
public class AssemblePoint implements Runnable {

    private final point point;

    public AssemblePoint(point p) {
        this.point = p;
    }

    @Override
    public void run() {
        try {
            // 模拟先各自独立运行
            Thread.sleep((int) (Math.random() * 1000));

            point.await();

            System.out.println(Thread.currentThread().getName() + "arrived");
            // ... 集合后执行其他操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class point {
        // 需要等待的个数
        private int num;

        public point(int num) {
            this.num = num;
        }

        public synchronized void await() throws InterruptedException {
            if (num > 0) {
                num--;
                if (num == 0) {
                    notifyAll();
                } else {
                    while (num != 0) {
                        wait();
                    }
                }
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        int size = 10;
        point point = new point(size);

        for (int i = 0; i < size; i++) {
            new Thread(new AssemblePoint(point)).start();
        }
    }

}
