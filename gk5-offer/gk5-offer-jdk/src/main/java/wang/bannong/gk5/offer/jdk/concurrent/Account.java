package wang.bannong.gk5.offer.jdk.concurrent;

public class Account {

    private long amount;

    public synchronized long cun(long money) {
        amount += money;
        System.out.println(Thread.currentThread().getName() + "，存入金额【" + money + "】，账户总金额【 " + amount + "】");
        notifyAll();
        return amount;
    }

    public synchronized long qu(long money) throws InterruptedException {
        while (amount < money || amount <= 0){
            wait();
        }
        amount -= money;
        System.out.println(Thread.currentThread().getName() + "，取出金额【" + money + "】，账户总金额【 " + amount + "】");
        return amount;
    }
}
