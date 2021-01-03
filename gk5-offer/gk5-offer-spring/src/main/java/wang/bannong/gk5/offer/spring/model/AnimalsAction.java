package wang.bannong.gk5.offer.spring.model;

public class AnimalsAction {

    private Dog dog;

    public void run() {
        dog.run();
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
