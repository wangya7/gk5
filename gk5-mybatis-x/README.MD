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
  mappersPath: wang.bannong.gk5.test.mybatisx.mappersx.mapper
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
