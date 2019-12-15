package wang.bannong.gk5.small.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wang.bannong.gk5.small.common.entity.SysLogEntity;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogDao extends BaseMapper<SysLogEntity> {


    /**
     * 自定义分页查询
     *
     * @param page
     * @param params
     * @return
     */
    List<SysLogEntity> selectSysLogPage(IPage page, @Param("params") Map<String, Object> params);
}
