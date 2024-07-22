package wang.bannong.gk5.offer.jdk.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

public class StreamLimiter {
    @Test
    public void c1() {
        // 5标识 QPS，200ms 产生一个token
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());

        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
    }

    @Test
    public void c2() {
        // 5标识 QPS，200ms 产生一个token
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire(5));
        System.out.println(rateLimiter.acquire(1));
        System.out.println(rateLimiter.acquire(1));
        System.out.println(rateLimiter.acquire(1));
    }
}
