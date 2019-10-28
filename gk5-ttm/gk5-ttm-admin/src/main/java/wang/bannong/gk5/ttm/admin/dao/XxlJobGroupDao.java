package wang.bannong.gk5.ttm.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import wang.bannong.gk5.ttm.admin.core.model.XxlJobGroup;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
public interface XxlJobGroupDao {

    List<XxlJobGroup> findAll();

    List<XxlJobGroup> findByAddressType(@Param("addressType") int addressType);

    int save(XxlJobGroup xxlJobGroup);

    int update(XxlJobGroup xxlJobGroup);

    int remove(@Param("id") int id);

    XxlJobGroup load(@Param("id") int id);
}
