package wang.bannong.gk5.gate.handler;

import wang.bannong.gk5.gate.domain.GateInnerRequest;
import wang.bannong.gk5.gate.domain.GateResult;

/**
 * Created by bn. on 2018/10/27 9:44 AM
 */
public interface IAuthorityAccess {

    GateResult access(GateInnerRequest request);

}
