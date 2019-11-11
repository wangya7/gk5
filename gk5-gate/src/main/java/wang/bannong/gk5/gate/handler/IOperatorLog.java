package wang.bannong.gk5.gate.handler;

import wang.bannong.gk5.gate.domain.GateRequest;
import wang.bannong.gk5.gate.domain.GateResult;
import wang.bannong.gk5.gate.domain.UserAuthToken;

/**
 * 后台操作记录
 * Created by bn. on 2018/11/19 9:40 AM
 */
public interface IOperatorLog {
    void log(UserAuthToken userAuthToken, GateRequest request);
}
