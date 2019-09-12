package wang.bannong.gk5.administrative.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by bn. on 2019/4/4 5:09 PM
 */
@Data
public class Administrative implements Serializable {
    private static final long serialVersionUID = -2640246812679055428L;
    private int    code;
    private String name;
}
