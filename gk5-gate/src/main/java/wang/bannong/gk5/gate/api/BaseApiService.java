package wang.bannong.gk5.gate.api;

import wang.bannong.gk5.gate.domain.GateResult;
import wang.bannong.gk5.gate.domain.GateInnerRequest;

/**
 * Created by wang.bannong on 2018/5/13 下午9:25
 */
@FunctionalInterface
public interface BaseApiService {
    GateResult api(GateInnerRequest request);
}
