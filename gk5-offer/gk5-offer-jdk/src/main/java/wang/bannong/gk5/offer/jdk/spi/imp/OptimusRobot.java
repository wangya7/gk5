package wang.bannong.gk5.offer.jdk.spi.imp;

import wang.bannong.gk5.offer.jdk.spi.Robot;

public class OptimusRobot implements Robot {
    @Override
    public void sayHi() {
        System.out.println("Hi, OptimusRobot");
    }
}
