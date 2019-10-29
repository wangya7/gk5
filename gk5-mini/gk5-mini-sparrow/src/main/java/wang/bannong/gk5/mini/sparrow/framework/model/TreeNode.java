package wang.bannong.gk5.mini.sparrow.framework.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 树形节点
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TreeNode extends BaseModel {
    private static final long serialVersionUID = 1L;

    protected Integer parentId;

    protected List<TreeNode> childrens = new ArrayList<>();
}
