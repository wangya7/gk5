package wang.bannong.gk5.iam.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.iam.dao.SysUserDao;

@Slf4j
@Component
public class SysUserMgr {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 组织下时候存在有效员工
     *
     * @param orgId 组织ID
     * @return F-不存在 T-存在
     */
    public boolean isExistStaff(Long orgId) throws Exception {
        return false;
    }
}
