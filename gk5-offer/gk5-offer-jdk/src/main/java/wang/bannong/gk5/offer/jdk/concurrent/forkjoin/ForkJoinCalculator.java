package wang.bannong.gk5.offer.jdk.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinCalculator implements Calculator {

    private final ForkJoinPool pool;

    public ForkJoinCalculator(ForkJoinPool pool) {
        this.pool = pool;
    }


    private class SumTask extends RecursiveTask<Long> {
        private static final long serialVersionUID = 1074727349190811778L;

        private final long[] numbers;
        private final int from;
        private final int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于6时，直接计算结果
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else { // 否则，把任务一分为二，递归计算
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle+1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }

    @Override
    public long sumUp(long[] numbers) {
        return pool.invoke(new SumTask(numbers, 0, numbers.length-1));
    }

    public static void main(String[] args) {
        long[] arr = LongStream.rangeClosed(1, 10000).toArray();
        long sum = new ForkJoinCalculator(new ForkJoinPool()).sumUp(arr);
        System.out.println(sum);  // 50005000
    }
}
