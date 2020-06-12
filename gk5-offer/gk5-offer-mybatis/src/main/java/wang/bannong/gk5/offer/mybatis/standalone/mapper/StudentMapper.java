package wang.bannong.gk5.offer.mybatis.standalone.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import wang.bannong.gk5.offer.mybatis.domain.Student;

public interface StudentMapper {
    Student selectOne(Integer id);
    int updateName(@Param("id") Integer id, @Param("name") String name);
    List<Student> selectByNameLike(String name);
}
