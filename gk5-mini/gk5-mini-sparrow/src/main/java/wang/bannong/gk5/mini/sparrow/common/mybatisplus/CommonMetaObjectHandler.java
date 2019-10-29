package wang.bannong.gk5.mini.sparrow.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

import wang.bannong.gk5.mini.sparrow.common.utils.ApiUtils;

/**
 * 通用填充类 适用于mybatis plus
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时间
     */
    private final String createTime = "createTime";
    /**
     * 修改时间
     */
    private final String updateTime = "updateTime";
    /**
     * 创建者ID
     */
    private final String createUid = "createUid";

    /**
     * 修改者ID
     */
    private final String updateUid = "updateUid";

    @Override
    public void insertFill(MetaObject metaObject) {
        setInsertFieldValByName(createTime, LocalDateTime.now(), metaObject);
        setInsertFieldValByName(createUid, currentUid(), metaObject);
        setInsertFieldValByName(updateTime, LocalDateTime.now(), metaObject);
        setInsertFieldValByName(updateUid, currentUid(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName(updateTime, LocalDateTime.now(), metaObject);
        setUpdateFieldValByName(updateUid, currentUid(), metaObject);
    }

    /**
     * 获取当前用户ID
     */
    private Integer currentUid() {
        Integer uid = null;
        try {
            uid = ApiUtils.currentUid();
        } catch (Exception ignored) {
        }
        return uid;
    }

}
