package wang.bannong.gk5.small.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wang.bannong.gk5.small.common.entity.SysOssEntity;
import wang.bannong.gk5.small.common.utils.PageUtilsPlus;

import java.util.Map;

/**
 * 文件上传Service
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @gitee https://gitee.com/fuyang_lipengjun/platform
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService extends IService<SysOssEntity> {

    /**
     * queryPage
     *
     * @param params
     * @return
     */
    PageUtilsPlus queryPage(Map<String, Object> params);
}
