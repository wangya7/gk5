package wang.bannong.gk5.offer.jdk.reflection.aop;

public class CGLibContainerDemo {
    public static void main(String[] args) {
        ServiceA a = CGLibContainer.getInstance(ServiceA.class);
        a.callB();
    }
}
