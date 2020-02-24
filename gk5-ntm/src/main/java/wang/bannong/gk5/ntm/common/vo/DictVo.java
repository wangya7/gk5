package wang.bannong.gk5.ntm.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class DictVo implements Serializable {
        
    private static final long serialVersionUID = -7758954074765013097L;

   /**
    * 名称
    */
   private String name;
   
   /**
    * 编码
    */
   private String code;
   
   /**
    * 标识
    */
   private String sn;
   
   /**
    * 备注
    */
   private String remark;
}
