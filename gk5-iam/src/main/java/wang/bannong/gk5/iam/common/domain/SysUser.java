package wang.bannong.gk5.iam.common.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 8085383158354040335L;

    private Long   id;
    private Long   orgId;
    private String mobile;
    private String identity;
    private String password;
    private String workNumber;
    private String icon;
    private String email;
    private String name;
    private Byte   status;
    private Date   lastLoginTime;
    private Long   creatorId;
    private Date   createTime;
    private Date   modifyTime;
}