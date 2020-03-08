package wang.bannong.gk5.ntm.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.ntm.biz.inner.NtmApiMgr;
import wang.bannong.gk5.ntm.biz.service.BaseInnerService;
import wang.bannong.gk5.ntm.common.dto.ApiParamDto;
import wang.bannong.gk5.ntm.common.dto.DynamicDto;
import wang.bannong.gk5.ntm.common.exception.NtmException;
import wang.bannong.gk5.ntm.common.model.NtmInnerRequest;
import wang.bannong.gk5.ntm.common.model.NtmResult;

@Slf4j
@Service
public class InnerApiParamsModify implements BaseInnerService {
    @Autowired
    private NtmApiMgr ntmApiMgr;
    @Override
    public NtmResult api(NtmInnerRequest innerRequest) {
        ApiParamDto dto = DynamicDto.of(innerRequest).get(ApiParamDto.class);
        try {
            return ntmApiMgr.modifyApiParam(dto);
        } catch (Exception e) {
            log.error("修改接口参数报错，dto={}，异常信息：", dto, e);
            throw new NtmException(e);
        }
    }
}