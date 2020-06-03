package wang.bannong.gk5.offer.mybatis.standalone;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import wang.bannong.gk5.offer.mybatis.domain.Student;
import wang.bannong.gk5.offer.mybatis.standalone.mapper.StudentMapper;

public class MybatisStandalone {

    private final static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-standalone.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void selectOne1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Student student = sqlSession.selectOne("wang.bannong.gk5.offer.mybatis.standalone.mapper.StudentMapper.selectOne", 2);
        System.out.println("Result=");
        System.out.println("       " + student);

    }

    @Test
    public void selectOne2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.selectOne(2);
        System.out.println("Result=");
        System.out.println("       " + student);
    }


    @Test
    public void selectList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByNameLike("B");
        System.out.println("Result=");
        System.out.println("       " + students);
    }
}
