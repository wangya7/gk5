项目参考：https://gitee.com/xuxueli0323/xxl-job

部署步骤如下：
1. 初始化数据库，文件所在位置：taoism-ttm-doc/db/tables_xxl_job.sql
2. 部署ADMIN
    * 修改服务端口、数据库连接、邮箱提醒 文件：taoism-ttm-admin/src/main/resources/application.properties
    * 修改打印日志目录 文件：taoism-ttm-admin/src/main/resources/logback.xml
3. 部署EXECUTOR方案一
    * ADMIN路径，JOB的处理日志路径 文件：taoism-ttm-executor/src/main/resources/application.properties
    * 系统打印路径 文件：taoism-ttm-executor/src/main/resources/logback.xml
4. 部署EXECUTOR方案二（推荐）
    * 查看 taoism-test项目， 编写 
    hz.qxbn.taoism.test.ttm.config.TtmConfigProperties.java
    hz.qxbn.taoism.test.ttm.TtmBeanGenerator.java
    * 添加配置项在 application.yml中
```
ttm:
  # xxl-job admin address list, such as "http://address" or "http://address01,http://address02"
  adminAddresses: http://127.0.0.1:9100/ttm-admin
  appName: ttm-executor-sample
  ip:
  port: 9999
  accessToken:
  logPath: /Users/wangya/Downloads/logs/jobhandler
  logRetentionDays: -1
```