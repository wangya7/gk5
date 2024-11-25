package wang.bannong.gk5.json;

public class Staff {
    private String  name;
    private Integer age;
    private Byte    category;
    private String  occupation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Staff{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", category=" + category +
            ", occupation='" + occupation + '\'' +
            '}';
    }
}
