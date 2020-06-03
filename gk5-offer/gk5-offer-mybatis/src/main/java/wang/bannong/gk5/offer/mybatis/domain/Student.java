package wang.bannong.gk5.offer.mybatis.domain;

public class Student {
    private Integer         id;
    private String          name;
    private Integer         age;
    private String          num;
    private StudentTypeEnum type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public StudentTypeEnum getType() {
        return type;
    }

    public void setType(StudentTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", num='" + num + '\'' +
                ", type=" + type +
                '}';
    }
}
