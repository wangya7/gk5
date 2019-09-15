package wang.bannong.gk5.gate.handler;


import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.gate.meta.GateMeta;

/**
 * Created by wang.bannong on 2018/5/13 下午9:03
 */
public final class MetaHandler {

    public static GateMeta fillMeta(GateRequest request) {
        GateMeta meta = new GateMeta();
        meta.setApi(request.getApi());
        meta.setTtid(request.getTtid());
        meta.setV(request.getV());
        meta.setMat(request.getMat());
        return meta;
    }

}
