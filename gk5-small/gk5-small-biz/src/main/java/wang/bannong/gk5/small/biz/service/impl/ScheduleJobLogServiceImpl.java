package wang.bannong.gk5.small.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import wang.bannong.gk5.small.biz.service.ScheduleJobLogService;
import wang.bannong.gk5.small.common.entity.ScheduleJobLogEntity;
import wang.bannong.gk5.small.common.utils.PageUtilsPlus;
import wang.bannong.gk5.small.common.utils.QueryPlus;
import wang.bannong.gk5.small.dao.ScheduleJobLogDao;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity>
        implements ScheduleJobLogService {

    @Override
    public PageUtilsPlus queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.CREATE_TIME");
        params.put("asc", false);
        Page<ScheduleJobLogEntity> page = new QueryPlus<ScheduleJobLogEntity>(params).getPage();
        return new PageUtilsPlus(page.setRecords(baseMapper.selectScheduleJobLogPage(page, params)));
    }
}
