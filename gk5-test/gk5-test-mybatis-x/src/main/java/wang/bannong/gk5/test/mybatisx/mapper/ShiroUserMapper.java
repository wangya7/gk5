package wang.bannong.gk5.test.mybatisx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import wang.bannong.gk5.test.mybatisx.common.ShiroUser;

public interface ShiroUserMapper extends BaseMapper<ShiroUser> {

    ShiroUser selectByName(String name);

}
