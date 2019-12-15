package wang.bannong.gk5.small.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.bannong.gk5.small.common.entity.SysUserEntity;
import wang.bannong.gk5.small.common.utils.ShiroUtils;

/**
 * Controller公共组件
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
