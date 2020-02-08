package wang.bannong.gk5.offer.mybatis.standalone.mapper;

import wang.bannong.gk5.offer.mybatis.domain.Student;

public interface StudentMapper {
    Student selectOne(Integer id);
}
