package wang.bannong.gk5.offer.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import wang.bannong.gk5.offer.mybatis.domain.Student;
import wang.bannong.gk5.offer.mybatis.standalone.mapper.StudentMapper;

public class MultiRead {
    private SqlSessionFactory sqlSessionFactory;
    private CountDownLatch    countDownLatch = new CountDownLatch(1);
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");

    @Before
    public void prepare() throws IOException {
        String resource = "mybatis-standalone.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }


    @Test
    public void testTransactional() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(2); // 开启两个线程
        Future<String> fa = es.submit(this::transactionalA);
        Future<String> fb = es.submit(this::transactionalB);
        countDownLatch.countDown();
        es.awaitTermination(6, TimeUnit.SECONDS);
        System.out.println(fa.get());
        System.out.println("\n" + time() + " -------- 分割线 ------- \n");
        System.out.println(fb.get());
    }


    private String transactionalA() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentDao = sqlSession.getMapper(StudentMapper.class);
        countDownLatch.await();
        StringBuilder sb = new StringBuilder();
        sb.append(time() + "时刻1:开启事务 A\n");
        sb.append(time() + "时刻2:查询记录 A\n");
        Student s1 = studentDao.selectOne(1);
        sb.append(s1).append("\n");
        sb.append(time() + "时刻3:更新记录 A\n");
        studentDao.updateName(1, "Clinton");
        sb.append(time() + "时刻4:查询记录 A'\n");
        Student s2 = studentDao.selectOne(1);
        sb.append(s2).append("\n");
        // 此处睡眠 1 秒，让事务 B 在事务 A 􏰀交前，完成时刻 4 的查询请求
        Thread.sleep(1000);
        sb.append(time() + "时刻5:􏰀提交事务 A\n");
        sqlSession.commit();
        sb.append(time() + "时刻6:􏰀提交事务 A 完成\n");
        return sb.toString();
    }

    private String transactionalB() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentDao = sqlSession.getMapper(StudentMapper.class);
        countDownLatch.await();
        StringBuilder sb = new StringBuilder();
        sb.append(time() + "时刻1:开启事务 B\n");
        sb.append(time() + "时刻2:查询数据 A\n");
        Student s1 = studentDao.selectOne(1);
        sb.append(s1).append("\n");
        sb.append(time() + "时刻3:---------\n");
        sb.append(time() + "时刻4:查询数据 A\n");
        Student s2 = studentDao.selectOne(1);
        sb.append(s2).append("\n");
        // 此处睡眠 3 秒，等待事务 A 􏰀交
        Thread.sleep(3000);
        sb.append(time() + "时刻5:---------\n");
        sb.append(time() + "时刻6:查询数据 A'\n");
        Student s3 = studentDao.selectOne(1);
        sb.append(s3).append("\n");
        sb.append(time() + "时刻7:􏰀提交事务 B\n");
        sqlSession.commit();
        sb.append(time() + "时刻8:提交事务 B 完成\n");
        return sb.toString();
    }

    private String time() {
        return dateFormat.format(new Date()) + "   ";
    }
}