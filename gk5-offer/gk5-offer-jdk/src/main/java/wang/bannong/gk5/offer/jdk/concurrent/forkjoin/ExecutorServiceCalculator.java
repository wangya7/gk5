package wang.bannong.gk5.offer.jdk.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

public class ExecutorServiceCalculator implements Calculator {
    private final int             parallism;
    private final ExecutorService service;

    public ExecutorServiceCalculator() {
        this.parallism = Runtime.getRuntime().availableProcessors();
        this.service = Executors.newFixedThreadPool(parallism);
    }


    private class SumTask implements Callable<Long> {
        private final long[] numbers;
        private final int    from;
        private final int    to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() throws Exception {
            long total = 0;
            for (int i = from; i <= to; i++) {
                total += numbers[i];
            }
            return total;
        }
    }

    @Override
    public long sumUp(long[] numbers) {
        List<Future<Long>> futures = new ArrayList<>();
        // 把任务分解为 n 份，交给 n 个线程处理
        int part = numbers.length / parallism;
        for (int i = 0; i < parallism; i++) {
            int from = i * part;
            int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1;
            futures.add(service.submit(new SumTask(numbers, from, to)));
        }

        long sum = 0;
        for (Future<Long> i : futures) {
            try {
                sum += i.get();
            } catch (Exception ignore) {
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long[] arr = LongStream.rangeClosed(1, 10000).toArray();
        long sum = new ExecutorServiceCalculator().sumUp(arr);
        System.out.println(sum);  // 50005000
        printTest(arr);
    }

    public static void printTest(long[] arr) {
        long sum = 0;
        for (long i : arr) {
            sum += i;
        }
        System.out.println(sum);
    }
}
