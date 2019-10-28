package wang.bannong.gk5.ttm.executor.jobhandler;

import org.springframework.stereotype.Service;

import wang.bannong.gk5.ttm.core.biz.model.ReturnT;
import wang.bannong.gk5.ttm.core.handler.IJobHandler;
import wang.bannong.gk5.ttm.core.handler.annotation.JobHandler;
import wang.bannong.gk5.ttm.core.log.XxlJobLogger;
import wang.bannong.gk5.ttm.core.util.ShardingUtil;

/**
 * 分片广播任务
 *
 * @author xuxueli 2017-07-25 20:56:50
 */
@JobHandler(value="shardingJobHandler")
@Service
public class ShardingJobHandler extends IJobHandler {

	@Override
	public ReturnT<String> execute(String param) throws Exception {

		// 分片参数
		ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
		XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());

		// 业务逻辑
		for (int i = 0; i < shardingVO.getTotal(); i++) {
			if (i == shardingVO.getIndex()) {
				XxlJobLogger.log("第 {} 片, 命中分片开始处理", i);
			} else {
				XxlJobLogger.log("第 {} 片, 忽略", i);
			}
		}

		return SUCCESS;
	}

}
