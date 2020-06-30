package wang.bannong.gk5.offer.mybatis.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

import lombok.Data;
import wang.bannong.gk5.offer.mybatis.enums.StudentTypeEnum;

@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 3080930808242709121L;

    private Integer         id;
    private String          name;
    private Integer         age;
    private String          num;
    private StudentTypeEnum type;
}
