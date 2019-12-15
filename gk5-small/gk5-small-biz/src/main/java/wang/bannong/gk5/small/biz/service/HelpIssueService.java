package wang.bannong.gk5.small.biz.service;

import wang.bannong.gk5.small.common.entity.HelpIssueEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-11-07 10:09:54
 */
public interface HelpIssueService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    HelpIssueEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<HelpIssueEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param helpIssue 实体
     * @return 保存条数
     */
    int save(HelpIssueEntity helpIssue);

    /**
     * 根据主键更新实体
     *
     * @param helpIssue 实体
     * @return 更新条数
     */
    int update(HelpIssueEntity helpIssue);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
