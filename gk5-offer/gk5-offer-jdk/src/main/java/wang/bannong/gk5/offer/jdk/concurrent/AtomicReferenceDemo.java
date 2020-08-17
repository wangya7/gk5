package wang.bannong.gk5.offer.jdk.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import lombok.ToString;

public class AtomicReferenceDemo {

    @ToString
    static class Pair {
        final private int first;
        final private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }

    @Test
    public void AtomicReference() {
        Pair p = new Pair(100, 200);
        AtomicReference<Pair> pairRef = new AtomicReference<>(p);
        pairRef.compareAndSet(p, new Pair(200, 200));

        System.out.println(pairRef.get().getFirst());
    }

    @Test
    public void AtomicStampedReference() {
        Pair pair = new Pair(100, 200);
        int stamp = 1;

        AtomicStampedReference<Pair> pairRef = new AtomicStampedReference<>(pair, stamp);
        int newStamp = 2;

        pairRef.compareAndSet(pair, new Pair(200, 200), stamp, newStamp);

        System.out.println(pairRef.getReference().getFirst());
    }

}
