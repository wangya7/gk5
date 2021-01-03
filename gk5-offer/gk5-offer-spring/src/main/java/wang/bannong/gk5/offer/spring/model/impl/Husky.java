package wang.bannong.gk5.offer.spring.model.impl;

import wang.bannong.gk5.offer.spring.model.Dog;

public class Husky implements Dog {
    @Override
    public void run() {
        System.out.println("Husky is running");
    }

    @Override
    public String hello(String name) {
        return "Husky say hello:" + name;
    }
}
