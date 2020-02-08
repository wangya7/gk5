package wang.bannong.gk5.offer.mybatis.standalone;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

import wang.bannong.gk5.offer.mybatis.domain.Student;

public class MybatisStandalone {

    public static SqlSessionFactory loadXml() throws IOException {
        String resource = "mybatis-standalone.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static void main(String... args) {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = loadXml();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Student student = sqlSession.selectOne("wang.bannong.gk5.offer.mybatis.standalone.mapper.StudentMapper.selectOne", 2);
            System.out.println("Result=");
            System.out.println("       " + student);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
