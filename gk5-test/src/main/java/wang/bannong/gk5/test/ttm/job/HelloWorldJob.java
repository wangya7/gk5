package wang.bannong.gk5.test.ttm.job;

import org.springframework.stereotype.Component;

import wang.bannong.gk5.ttm.core.biz.model.ReturnT;
import wang.bannong.gk5.ttm.core.handler.IJobHandler;
import wang.bannong.gk5.ttm.core.handler.annotation.JobHandler;
import wang.bannong.gk5.ttm.core.log.XxlJobLogger;

@JobHandler(value = "helloWorldJob")
@Component
public class HelloWorldJob extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        XxlJobLogger.log("啦啦啦德玛西亚，王者峡谷见！！！！");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
        }
        return SUCCESS;
    }
}
