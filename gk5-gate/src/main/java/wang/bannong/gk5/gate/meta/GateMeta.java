package wang.bannong.gk5.gate.meta;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wang.bannong on 2018/5/13 下午8:08
 */
@Data
public class GateMeta implements Serializable {

    private static final long serialVersionUID = -8076552220470392854L;
    private String api;
    private String v;
    private String ttid;
    private String mat;

}
