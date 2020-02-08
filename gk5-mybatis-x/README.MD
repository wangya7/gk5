### Mybatis-Plus使用篇
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

