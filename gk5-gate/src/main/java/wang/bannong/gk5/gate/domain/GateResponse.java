package wang.bannong.gk5.gate.domain;

import lombok.Data;
import wang.bannong.gk5.gate.meta.GateMeta;

import java.io.Serializable;

/**
 * Created by wang.bannong on 2018/5/13 下午8:07
 */
@Data
public class GateResponse implements Serializable {

    private static final long serialVersionUID = 296387297310975229L;

    private GateMeta meta;
    private GateResult response;

    public static GateResponse of(GateResult dataBus){
        GateResponse response = new GateResponse();
        response.setResponse(dataBus);
        return response;
    }

    public static GateResponse of(Object model){
        if(model instanceof GateResult){
            return of((GateResult)model);
        }

        GateResponse response = new GateResponse();
        response.setResponse(GateResult.of(model));
        return response;
    }

}
