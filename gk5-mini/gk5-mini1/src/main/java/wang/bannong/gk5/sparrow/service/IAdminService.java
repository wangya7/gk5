package wang.bannong.gk5.sparrow.service;

import java.util.List;

import wang.bannong.gk5.sparrow.model.dto.TokenDTO;
import wang.bannong.gk5.sparrow.model.dto.AdminDetailsDTO;
import wang.bannong.gk5.sparrow.model.entity.Admin;
import wang.bannong.gk5.sparrow.enums.StatusEnum;
import wang.bannong.gk5.sparrow.framework.service.BaseService;

/**
 * 系统用户表 服务类
 */
public interface IAdminService extends BaseService<Admin> {

    /**
     * 用户登陆
     *
     * @param loginName
     * @param password
     * @param ipAddr
     * @return
     */
    Admin login(String loginName, String password, String ipAddr);

    TokenDTO getToken(Admin admin);

    AdminDetailsDTO getUserDetails(Integer uid);

    /**
     * 用户修改密码
     *
     * @param uid
     * @param oldPassword
     * @param newPassword
     * @return
     */
    void updatePassword(Integer uid, String oldPassword, String newPassword);

    /**
     * 重置用户密码
     *
     * @param uid
     */
    void resetPwd(Integer uid);

    /**
     * @param uid
     * @param status
     */
    void updateStatus(Integer uid, StatusEnum status);

    /**
     * 添加用户角色
     *
     * @param uid
     * @param roleIds
     */
    void saveUserRoles(Integer uid, List<Integer> roleIds);

    /**
     * 根据用户ID获取角色
     *
     * @param uid
     * @return
     */
    List<Integer> getRoleIds(Integer uid);

}
