package wang.bannong.gk5.ntm.biz.handler;

import wang.bannong.gk5.ntm.common.model.NtmInnerRequest;
import wang.bannong.gk5.ntm.common.model.NtmResult;

@FunctionalInterface
public interface IAuthorityAccess {
    NtmResult access(NtmInnerRequest innerRequest);
}
