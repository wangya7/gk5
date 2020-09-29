package wang.bannong.gk5.offer.jdk.spi.imp;

import wang.bannong.gk5.offer.jdk.spi.Robot;

public class BumbleRobot implements Robot {
    @Override
    public void sayHi() {
        System.out.println("Hi, BumbleRobot");
    }
}
