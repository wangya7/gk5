package wang.bannong.gk5.offer.jdk.concurrent;

public interface MyFuture<R> {
    R get() throws Exception;
}
