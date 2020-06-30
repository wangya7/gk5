package wang.bannong.gk5.offer.mybatis.standalone;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationBuilderTest {

    @Test
    public void build() throws IOException {
        String resource = "mybatis-standalone.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 通过输入流转换成XPathParser
        XMLConfigBuilder builder = new XMLConfigBuilder(inputStream);
        Configuration configuration = builder.parse();

        SqlSessionFactory defaultSqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();
    }

}
