package wang.bannong.gk5.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.test.common.ShiroUser;
import wang.bannong.gk5.test.mapper.ShiroUserMapper;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ImportResource("classpath:applicationContext.xml")
public class MybatisXTest {

    @Autowired
    private ShiroUserMapper masetrShiroUserMapper;
    @Autowired
    private ShiroUserMapper slaveShiroUserMapper;

    @Test
    public void multDataSource() {
        ShiroUser record = new ShiroUser();
        record.setId(11);
        record.setName("HG");
        record.setPasswd("HG");
        record.setRole("HG");
        record.setPermission("HG");
        masetrShiroUserMapper.insert(record);

        ShiroUser shiroUser = slaveShiroUserMapper.selectById(11);
        log.info("Rs={}", shiroUser);
    }
}
