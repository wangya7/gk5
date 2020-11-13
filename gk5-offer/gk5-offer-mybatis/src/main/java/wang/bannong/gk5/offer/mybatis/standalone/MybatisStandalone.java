package wang.bannong.gk5.offer.mybatis.standalone;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.ParamNameResolver;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import wang.bannong.gk5.offer.mybatis.domain.Student;
import wang.bannong.gk5.offer.mybatis.standalone.mapper.StudentMapper;

public class MybatisStandalone {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() {
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
        Student student = studentMapper.selectByIdAndName(2, "Tom");
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

    // 插件测试
    @Test
    public void selectPageByInterceptor() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.findByPaging(2, new RowBounds(2, 2));
        System.out.println("Result=");
        System.out.println("       " + students);
    }

    @Test
    public void paramNameResolverTest() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        Configuration config = new Configuration();
        config.setUseActualParamName(false);
        Method method = StudentMapper.class.getMethod("select", Integer.class, String.class,
                RowBounds.class, Student.class);

        ParamNameResolver resolver = new ParamNameResolver(config, method);

        Field field = resolver.getClass().getDeclaredField("names");
        field.setAccessible(true);
        // 通过反射获取 ParamNameResolver 私有成员变量 names
        Object names = field.get(resolver);

        System.out.println("names: " + names);
    }

    @Test
    public void testWhereSqlNode() throws IOException {
        String sqlFragment = "AND id = #{id}";
        MixedSqlNode msn = new MixedSqlNode(Arrays.asList(new StaticTextSqlNode(sqlFragment)));
        WhereSqlNode wsn = new WhereSqlNode(new Configuration(), msn);

        DynamicContext dc = new DynamicContext(new Configuration(), new MapperMethod.ParamMap<>());
        wsn.apply(dc);

        System.out.println("解析前:" + sqlFragment);
        System.out.println("解析后:" + dc.getSql());
    }

    @Test
    public void ParameterMappingTokenHandler_buildParameterMapping_Test() {
        // 带有复杂 #{} 占位符的参数，接下里会解析这个占位符
        String sql = "select id, name, age, num, type from student where id=#{id} and name = #{name}";
        SqlSourceBuilder sqlSourceBuilder = new SqlSourceBuilder(new Configuration());
        SqlSource sqlSource = sqlSourceBuilder.parse(sql, Student.class, new HashMap<>());
        BoundSql boundSql = sqlSource.getBoundSql(new Student());

        System.out.println(String.format("SQL: %s\n", boundSql.getSql()));
        System.out.println(String.format("ParameterMappings: %s", boundSql.getParameterMappings()));
    }
}
