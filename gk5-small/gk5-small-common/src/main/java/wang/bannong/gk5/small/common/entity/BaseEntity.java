package wang.bannong.gk5.small.common.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 对业务实体做公共属性
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年11月16日 下午10:43:36
 */
@Data
public class BaseEntity {
    private String       createId;         // 新增人
    private String       updateId;         // 修改者
    private String       remark;           // 备注
    private Date         createTime;       // 新增时间
    private Date         updateTime;       // 更新时间
    private Long         deptId;           // 部门(组织)ID【FK】,直接归属的组织ID
    private String       deptParentId;     // 机构ID【FK】(上级)
    private List<Long>   deptIdList;       // 部门ids 部门数据权限
    private List<String> deptParentIdList; // 机构ids 机构数据权限
    private String       deptName;         // 部门名称
    private String       deptParentName;   // 机构名称

}
