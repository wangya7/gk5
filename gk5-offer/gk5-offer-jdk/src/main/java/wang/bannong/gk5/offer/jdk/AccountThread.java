package wang.bannong.gk5.offer.jdk;

import java.util.Random;

import wang.bannong.gk5.offer.jdk.concurrent.Account;

public class AccountThread {


    public static void main(String[] args) {
        Account account = new Account();
        account.cun(0);

        Random random = new Random();
        int size = 20;
        Thread[] cunthreads = new Thread[size];
        Thread[] quthreads = new Thread[size];
        for (int i = 0; i < size; i++) {
            cunthreads[i] = new Thread(() -> {
                long money = random.nextInt(100) + 1;
                account.cun(money);
            });
        }

        for (int i = 0; i < size; i++) {
            quthreads[i] = new Thread(() -> {
                long money = random.nextInt(100) + 1;
                try {
                    account.qu(money);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < size; i++) {
            quthreads[i].start();
        }

        for (int i = 0; i < size; i++) {
            cunthreads[i].start();
        }

    }
}
