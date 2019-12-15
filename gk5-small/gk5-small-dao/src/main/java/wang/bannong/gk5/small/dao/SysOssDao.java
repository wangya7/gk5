package wang.bannong.gk5.small.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wang.bannong.gk5.small.common.entity.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传Dao
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @gitee https://gitee.com/fuyang_lipengjun/platform
 * @date 2017-03-25 12:13:26
 */
public interface SysOssDao extends BaseMapper<SysOssEntity> {

    /**
     * 自定义分页查询
     *
     * @param page
     * @param params
     * @return
     */
    List<SysOssEntity> selectSysOssPage(IPage page, Map<String, Object> params);
}
