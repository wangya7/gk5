package wang.bannong.gk5.offer.mybatis.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 3080930808242709121L;

    private Integer id;
    private String          name;
    private Integer         age;
    private String          num;
    private StudentTypeEnum type;
}
