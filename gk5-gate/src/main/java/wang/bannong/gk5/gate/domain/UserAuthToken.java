package wang.bannong.gk5.gate.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import lombok.Data;

/**
 * Created by wang.bannong on 2018/5/26 下午12:31
 */
@Data
public abstract class UserAuthToken implements Serializable {
    private static final long serialVersionUID = 5655231798296157527L;
    private String              mat;            // token
    private Role                role;           // 0-用户 1-管理员
    private Date                createTime;     // 创建时间
    private Date                accessTime;     // 访问时间
    private Date                lastAccessTime; // 上次访问时间
    private Map<String, String> extra;          // 扩展数据

    public abstract long getUid();


    public enum Role implements Serializable {
        admin(1),
        user(0);
        int code;
        Role(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        public static Role of(int code) {
            for (Role role : values()) {
                if (code == role.getCode()) return role;
            }
            return null;
        }

        @Override
        public String toString() {
            return "Role{" +
                    "code=" + code +
                    "} " + super.toString();
        }
    }
}
