package wang.bannong.gk5.gate.handler;

import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.gate.domain.GateResponse;
import wang.bannong.gk5.gate.domain.UserAuthToken;

/**
 * 接口访问记录
 */
public interface IApiLog {
    void log(UserAuthToken userAuthToken, GateRequest request, GateResponse response);
}
