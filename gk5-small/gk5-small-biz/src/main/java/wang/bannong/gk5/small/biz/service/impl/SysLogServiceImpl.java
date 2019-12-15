package wang.bannong.gk5.small.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import wang.bannong.gk5.small.biz.service.SysLogService;
import wang.bannong.gk5.small.common.entity.SysLogEntity;
import wang.bannong.gk5.small.common.utils.PageUtilsPlus;
import wang.bannong.gk5.small.common.utils.QueryPlus;
import wang.bannong.gk5.small.dao.SysLogDao;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtilsPlus queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "create_date");
        params.put("asc", false);
        Page<SysLogEntity> page = new QueryPlus<SysLogEntity>(params).getPage();
        return new PageUtilsPlus(page.setRecords(baseMapper.selectSysLogPage(page, params)));
    }
}
