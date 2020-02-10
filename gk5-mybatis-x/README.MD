### Mybatis-Plus使用篇
> 2020/2/1 NCP隔离在家期间，武汉加油，中国加油


* [官网](https://mybatis.plus/)
* [Mybatis-Plus Gitee](https://gitee.com/baomidou/mybatis-plus)

个人观点：
1. Mybatis-Plus忽略了业务层，服务层直接操作数据库。
2. 交叉的业务不能很好支持，需要多张表关联查询

本项目希望实现:
1. 基于Mybatis，构造持久层DAO
2. 自动构造业务层，参考DDD
3. 支持多数据源
4. 支持SQL日志显示，执行时间

#### 快速开始
application.yml增加配置
```yaml
datasource:
  mappersPath: wang.bannong.gk5.test.mapper
  primary: master
  dbs:
    - key: master
      host: 127.0.0.1
      port: 3306
      db: gk5
      username: root
      password: '@iam3307'
      minIdle: 10
      maxIdle: 10
      connectionTimeout: 30000
    - key: slave
      host: 127.0.0.1
      port: 3306
      db: gk5
      username: root
      password: '@iam3307'
      minIdle: 10
      maxIdle: 10
      connectionTimeout: 30000
```
注意：
1. dbs中定义的数组key必须以master,slave开头
2. datasource.primary必须是dbs中以master开头的key
3. datasource.mappersPath暂时没有用，可以不配置

增加applicationContext.xml配置文件按照，上述配置的dbs增加对应的MapperScannerConfigurer配置
```xml
    <bean id="${key}MapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="wang.bannong.gk5.test.mapper" />
        <property name="sqlSessionTemplateBeanName" value="${key}SqlSessionTemplate" />
        <property name="nameGenerator" ref="${key}MapperBeanNameGenerator"/>
    </bean>
    <bean id="${key}MapperBeanNameGenerator" class="wang.bannong.gk5.mybatis.x.MapperBeanNameGenerator">
        <constructor-arg value="${key}"/>
    </bean>
```
例如上述的yml文件就需要配置成下面样子
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-4.1.xsd  
    http://www.springframework.org/schema/context  
    http://www.springframework.org/schema/context/spring-context-4.1.xsd">

    <!-- properties配置文件加载 -->
    <!--<context:property-placeholder location="classpath*:config.properties" ignore-unresolvable="true"/>-->

    <!-- 模板数据导入（目前只有code-name映射数据）-->
    <!--<import resource="template-data.xml"/>-->
    <bean id="masterMapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="wang.bannong.gk5.test.mapper" />
        <property name="sqlSessionTemplateBeanName" value="masterSqlSessionTemplate" />
        <property name="nameGenerator" ref="masterMapperBeanNameGenerator"/>
    </bean>
    <bean id="masterMapperBeanNameGenerator" class="wang.bannong.gk5.mybatis.x.MapperBeanNameGenerator">
        <constructor-arg value="master"/>
    </bean>

    <bean id="slaveMapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="wang.bannong.gk5.test.mapper" />
        <property name="sqlSessionTemplateBeanName" value="slaveSqlSessionTemplate" />
        <property name="nameGenerator" ref="slaveMapperBeanNameGenerator"/>
    </bean>
    <bean id="slaveMapperBeanNameGenerator" class="wang.bannong.gk5.mybatis.x.MapperBeanNameGenerator">
        <constructor-arg value="slave"/>
    </bean>

</beans>
```

