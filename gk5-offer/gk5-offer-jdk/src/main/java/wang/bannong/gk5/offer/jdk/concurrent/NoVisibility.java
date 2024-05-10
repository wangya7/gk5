package wang.bannong.gk5.offer.jdk.concurrent;

public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }
    public static void main(String...args) {
        new ReaderThread().start();
        number = 47;
        ready = true;
    }
}