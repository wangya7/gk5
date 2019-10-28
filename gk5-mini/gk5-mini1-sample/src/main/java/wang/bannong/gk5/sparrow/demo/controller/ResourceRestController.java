package wang.bannong.gk5.sparrow.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import wang.bannong.gk5.sparrow.common.annotations.Resources;
import wang.bannong.gk5.sparrow.demo.service.ScanMappings;
import wang.bannong.gk5.sparrow.enums.AuthTypeEnum;
import wang.bannong.gk5.sparrow.framework.controller.SuperController;
import wang.bannong.gk5.sparrow.framework.responses.ApiResponses;
import wang.bannong.gk5.sparrow.model.entity.Resource;
import wang.bannong.gk5.sparrow.service.IResourceService;

/**
 * 资源表 前端控制器
 */
@Api(tags = {"Resource"}, description = "资源操作相关接口")
@RestController
@RequestMapping(value = "/resources", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class ResourceRestController extends SuperController {

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private ScanMappings scanMappings;

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有资源(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceName", value = "需要查询的资源名", paramType = "query"),
            @ApiImplicitParam(name = "method", value = "需要查询的请求方式", paramType = "query"),
            @ApiImplicitParam(name = "authType", value = "权限认证类型", paramType = "query")
    })
    @GetMapping
    public ApiResponses<IPage<Resource>> page(@RequestParam(value = "resourceName", required = false) String resourceName,
                                              @RequestParam(value = "method", required = false) String method,
                                              @RequestParam(value = "authType", required = false) AuthTypeEnum authType
    ) {
        return success(
                resourceService.query()
                        .like(StringUtils.isNotEmpty(resourceName), Resource::getResourceName, resourceName)
                        .eq(StringUtils.isNotEmpty(method), Resource::getMethod, method)
                        .eq(Objects.nonNull(authType), Resource::getAuthType, authType)
                        .page(this.<Resource>getPage())
        );
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有资源")
    @GetMapping("/resources")
    public ApiResponses<List<Resource>> list() {
        return success(resourceService.list());
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "刷新资源")
    @PutMapping
    public ApiResponses<Void> refresh() {
        scanMappings.doScan();
        return success();
    }


}

