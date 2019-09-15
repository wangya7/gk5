package wang.bannong.gk5.sparrow.demo.service.impl;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import wang.bannong.gk5.sparrow.common.utils.JWTUtils;
import wang.bannong.gk5.sparrow.common.utils.TypeUtils;
import wang.bannong.gk5.sparrow.demo.mapper.UserMapper;
import wang.bannong.gk5.sparrow.enums.StatusEnum;
import wang.bannong.gk5.sparrow.framework.enums.ErrorCodeEnum;
import wang.bannong.gk5.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.sparrow.framework.utils.ApiAssert;
import wang.bannong.gk5.sparrow.model.dto.TokenDTO;
import wang.bannong.gk5.sparrow.model.dto.UserDetailsDTO;
import wang.bannong.gk5.sparrow.model.entity.User;
import wang.bannong.gk5.sparrow.model.entity.UserRole;
import wang.bannong.gk5.sparrow.service.IResourceService;
import wang.bannong.gk5.sparrow.service.IUserRoleService;
import wang.bannong.gk5.sparrow.service.IUserService;

/**
 * 系统用户表 服务实现类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    @Transactional
    public User login(String loginName, String password, String ipAddr) {
        User user = query().eq(User::getLoginName, loginName).getOne();
        //用户不存在
        ApiAssert.notNull(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG, user);
        //用户名密码错误
        ApiAssert.isTrue(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG, Md5Crypt.apr1Crypt(password, loginName).equals(user.getPassword()));
        //用户被禁用
        ApiAssert.isTrue(ErrorCodeEnum.USER_IS_DISABLED, StatusEnum.NORMAL.equals(user.getStatus()));
        user.setIp(ipAddr);
        updateById(user);
        return user;
    }

    @Override
    public TokenDTO getToken(User user) {
        Integer uid = user.getId();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUid(uid);
        tokenDTO.setToken(JWTUtils.generate(uid));
        return tokenDTO;
    }

    @Override
    public UserDetailsDTO getUserDetails(Integer uid) {
        User user = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, user);
        UserDetailsDTO userDetails = user.convert(UserDetailsDTO.class);
        userDetails.setPerms(resourceService.getUserPerms(uid));
        return userDetails;
    }

    @Override
    @Transactional
    public void updatePassword(Integer uid, String oldPassword, String newPassword) {
        User user = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, user);
        //用户名密码错误
        ApiAssert.isTrue(ErrorCodeEnum.ORIGINAL_PASSWORD_IS_INCORRECT, Md5Crypt.apr1Crypt(oldPassword, user.getLoginName()).equals(user.getPassword()));
        user.setPassword(Md5Crypt.apr1Crypt(newPassword, user.getLoginName()));
        updateById(user);
    }

    @Override
    @Transactional
    public void resetPwd(Integer uid) {
        User user = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, user);
        user.setPassword(Md5Crypt.apr1Crypt(user.getLoginName(), user.getLoginName()));
        updateById(user);
    }

    @Override
    @Transactional
    public void updateStatus(Integer uid, StatusEnum status) {
        User user = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, user);
        user.setStatus(status);
        updateById(user);
    }

    @Override
    @Transactional
    public void saveUserRoles(Integer uid, List<Integer> roleIds) {
        if (CollectionUtils.isNotEmpty(roleIds)) {
            userRoleService.delete().eq(UserRole::getUid, uid).execute();
            userRoleService.saveBatch(roleIds.stream().map(e -> new UserRole(uid, e)).collect(Collectors.toList()));
        }
    }

    @Override
    public List<Integer> getRoleIds(Integer uid) {
        return userRoleService.query().select(UserRole::getRoleId).eq(UserRole::getUid, uid).listObjs(TypeUtils::castToInt);
    }

    public static void main(String... args) {
        String password = "123456";
        String loginName = "admin";

        System.out.println(Md5Crypt.apr1Crypt(password, loginName));
    }

}
