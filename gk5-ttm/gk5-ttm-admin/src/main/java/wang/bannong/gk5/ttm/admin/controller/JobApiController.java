package wang.bannong.gk5.ttm.admin.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.bannong.gk5.ttm.admin.controller.annotation.PermissionLimit;
import wang.bannong.gk5.ttm.admin.core.conf.XxlJobScheduler;
import wang.bannong.gk5.ttm.core.biz.AdminBiz;

/**
 * Created by xuxueli on 17/5/10.
 */
@Controller
public class JobApiController implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @RequestMapping(AdminBiz.MAPPING)
    @PermissionLimit(limit=false)
    public void api(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        XxlJobScheduler.invokeAdminService(request, response);
    }


}
