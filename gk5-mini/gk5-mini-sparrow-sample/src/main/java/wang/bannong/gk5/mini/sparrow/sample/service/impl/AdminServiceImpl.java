package wang.bannong.gk5.mini.sparrow.sample.service.impl;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import wang.bannong.gk5.mini.sparrow.common.utils.JWTUtils;
import wang.bannong.gk5.mini.sparrow.common.utils.TypeUtils;
import wang.bannong.gk5.mini.sparrow.sample.mapper.AdminMapper;
import wang.bannong.gk5.mini.sparrow.enums.StatusEnum;
import wang.bannong.gk5.mini.sparrow.framework.enums.ErrorCodeEnum;
import wang.bannong.gk5.mini.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.mini.sparrow.framework.utils.ApiAssert;
import wang.bannong.gk5.mini.sparrow.model.dto.AdminDetailsDTO;
import wang.bannong.gk5.mini.sparrow.model.dto.TokenDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Admin;
import wang.bannong.gk5.mini.sparrow.model.entity.AdminRole;
import wang.bannong.gk5.mini.sparrow.service.IAdminRoleService;
import wang.bannong.gk5.mini.sparrow.service.IAdminService;
import wang.bannong.gk5.mini.sparrow.service.IResourceService;

/**
 * 系统用户表 服务实现类
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private IResourceService  resourceService;
    @Autowired
    private IAdminRoleService userRoleService;

    @Override
    @Transactional
    public Admin login(String loginName, String password, String ipAddr) {
        Admin admin = query().eq(Admin::getLoginName, loginName).getOne();
        //用户不存在
        ApiAssert.notNull(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG, admin);
        //用户名密码错误
        ApiAssert.isTrue(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG, Md5Crypt.apr1Crypt(password, loginName).equals(admin.getPassword()));
        //用户被禁用
        ApiAssert.isTrue(ErrorCodeEnum.USER_IS_DISABLED, StatusEnum.NORMAL.equals(admin.getStatus()));
        admin.setIp(ipAddr);
        updateById(admin);
        return admin;
    }

    @Override
    public TokenDTO getToken(Admin admin) {
        Integer uid = admin.getId();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUid(uid);
        tokenDTO.setToken(JWTUtils.generate(uid));
        return tokenDTO;
    }

    @Override
    public AdminDetailsDTO getUserDetails(Integer uid) {
        Admin admin = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, admin);
        AdminDetailsDTO userDetails = admin.convert(AdminDetailsDTO.class);
        userDetails.setPerms(resourceService.getUserPerms(uid));
        return userDetails;
    }

    @Override
    @Transactional
    public void updatePassword(Integer uid, String oldPassword, String newPassword) {
        Admin admin = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, admin);
        //用户名密码错误
        ApiAssert.isTrue(ErrorCodeEnum.ORIGINAL_PASSWORD_IS_INCORRECT, Md5Crypt.apr1Crypt(oldPassword, admin.getLoginName()).equals(admin.getPassword()));
        admin.setPassword(Md5Crypt.apr1Crypt(newPassword, admin.getLoginName()));
        updateById(admin);
    }

    @Override
    @Transactional
    public void resetPwd(Integer uid) {
        Admin admin = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, admin);
        admin.setPassword(Md5Crypt.apr1Crypt(admin.getLoginName(), admin.getLoginName()));
        updateById(admin);
    }

    @Override
    @Transactional
    public void updateStatus(Integer uid, StatusEnum status) {
        Admin admin = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, admin);
        admin.setStatus(status);
        updateById(admin);
    }

    @Override
    @Transactional
    public void saveUserRoles(Integer uid, List<Integer> roleIds) {
        if (CollectionUtils.isNotEmpty(roleIds)) {
            userRoleService.delete().eq(AdminRole::getUid, uid).execute();
            userRoleService.saveBatch(roleIds.stream().map(e -> new AdminRole(uid, e)).collect(Collectors.toList()));
        }
    }

    @Override
    public List<Integer> getRoleIds(Integer uid) {
        return userRoleService.query().select(AdminRole::getRoleId).eq(AdminRole::getUid, uid).listObjs(TypeUtils::castToInt);
    }

    public static void main(String... args) {
        String password = "123456";
        String loginName = "admin";

        System.out.println(Md5Crypt.apr1Crypt(password, loginName));
    }

}
