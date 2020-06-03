package wang.bannong.gk5.offer.mybatis.standalone.mapper;

import java.util.List;

import wang.bannong.gk5.offer.mybatis.domain.Student;

public interface StudentMapper {
    Student selectOne(Integer id);
    List<Student> selectByNameLike(String name);
}
