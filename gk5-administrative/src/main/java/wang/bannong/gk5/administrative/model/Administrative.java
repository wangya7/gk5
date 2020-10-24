package wang.bannong.gk5.administrative.model;

import java.io.Serializable;

/**
 * Created by bn. on 2019/4/4 5:09 PM
 */
public class Administrative implements Serializable {
    private static final long serialVersionUID = -2640246812679055428L;
    private int    code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Administrative{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
