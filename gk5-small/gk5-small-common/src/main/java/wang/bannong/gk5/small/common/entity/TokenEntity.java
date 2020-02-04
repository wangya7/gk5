package wang.bannong.gk5.small.common.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**
 * 用户Token
 */
@Data
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户ID
    private Long   userId;
    //token
    private String token;
    //过期时间
    private Date   expireTime;
    //更新时间
    private Date   updateTime;

}
