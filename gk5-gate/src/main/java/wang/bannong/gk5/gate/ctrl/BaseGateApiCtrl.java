package wang.bannong.gk5.gate.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.gate.config.GateConfig;
import wang.bannong.gk5.gate.handler.IOperatorLog;
import wang.bannong.gk5.gate.api.BaseApiService;
import wang.bannong.gk5.gate.domain.*;
import wang.bannong.gk5.gate.handler.CookieHandler;
import wang.bannong.gk5.gate.handler.GateCORSApiHandler;
import wang.bannong.gk5.gate.handler.IAuthorityAccess;
import wang.bannong.gk5.gate.handler.InnerRequestHandler;
import wang.bannong.gk5.gate.handler.RequestHandler;
import wang.bannong.gk5.gate.handler.ResponceHandler;
import wang.bannong.gk5.util.SpringBeanUtils;
import wang.bannong.gk5.util.TaskExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wang.bannong on 2018/5/13 下午8:05
 */
@Slf4j
@RestController
public class BaseGateApiCtrl {

    @Autowired
    protected      InnerRequestHandler innerRequestHandler;
    private static IAuthorityAccess    authorityAccess;
    private static IOperatorLog        operatorLog;

    @RequestMapping(value = "/fx", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GateResponse api(HttpServletRequest req, HttpServletResponse res) {
        // 1. 获取request对象
        GateRequest request = RequestHandler.convert2GateRequest(req);
        log.info("[GATE] 入参：{}", request);

        // 2. 跨域访问提前设置
        GateCORSApiHandler.setCORSSettingIfNeed(request, req, res);

        // 3. 基础API参数校验
        GateResult domain = RequestHandler.checkReuqest(request);
        if (null == domain || !domain.isSuccess()) {
            log.warn("[GATE] 入参异常：{}", domain);
            return ResponceHandler.fillResponse(request, domain);
        }

        // 4. 内部接口处理【authToken、用户、业务接口】
        GateInnerRequest innerRequest = GateInnerRequest.of(request);
        domain = innerRequestHandler.checkAndBuild(request, innerRequest);
        if (null == domain || !domain.isSuccess()) {
            log.warn("[GATE] 入参异常：{}", domain);
            return ResponceHandler.fillResponse(request, domain);
        }

        // 5. 处理响应结果
        GateResponse response = ResponceHandler.fillResponse(request, innerApi(innerRequest));
        if (GateConfig.logResponse) {
            log.info("[GATE] 出参：{}", response);
        }

        // 6. 设置cookie
        CookieHandler.setCookieSettingIfNeed(innerRequest, request, response, res);
        return response;
    }

    public static Object innerApi(GateInnerRequest innerRequest) {
        GateApiInfo apiInfo = innerRequest.getApiInfo();

        boolean isAdminAndNeedLogin = false;
        if (apiInfo.isNeedLogin() && GateApiChannel.admin == apiInfo.getChannel()) {
            isAdminAndNeedLogin = true;
            if (null == authorityAccess) {
                authorityAccess = SpringBeanUtils.getBean("authorityAccess", IAuthorityAccess.class);
            }
            GateResult result = authorityAccess.access(innerRequest);
            if (!result.isSuccess()) {
                return result;
            }
        }

        final String beanName = apiInfo.getBean();
        BaseApiService baseApi = SpringBeanUtils.getBean(beanName, BaseApiService.class);
        if (null == baseApi) {
            return GateResult.of(ErrorMsgEnum.api_service_notfound);
        }

        GateResult gateResult = baseApi.api(innerRequest);
        if (gateResult.isSuccess() && isAdminAndNeedLogin) {
            GateRequest request = innerRequest.getGateRequest();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (null == operatorLog) {
                    operatorLog = SpringBeanUtils.getBean("operatorLog", IOperatorLog.class);
                }
                TaskExecutor.excute(() -> operatorLog.log(innerRequest.getUserAuthToken(), request));
            }
        }

        return gateResult;
    }

}