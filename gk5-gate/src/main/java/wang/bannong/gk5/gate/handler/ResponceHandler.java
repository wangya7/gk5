package wang.bannong.gk5.gate.handler;


import wang.bannong.gk5.gate.domain.GateResult;
import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.gate.domain.GateResponse;

/**
 * Created by wang.bannong on 2018/5/13 下午9:02
 */
public final class ResponceHandler {

    public static GateResponse fillResponse(GateRequest request, GateResult result) {
        GateResponse response = GateResponse.of(result);
        response.setMeta(MetaHandler.fillMeta(request));
        return response;
    }

    public static GateResponse fillResponse(GateRequest request, Object module) {
        GateResponse response = GateResponse.of(module);
        response.setMeta(MetaHandler.fillMeta(request));
        return response;
    }

}
