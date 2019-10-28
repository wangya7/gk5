package wang.bannong.gk5.sparrow.demo.service;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import io.swagger.annotations.ApiOperation;
import liquibase.util.MD5Util;
import wang.bannong.gk5.sparrow.common.annotations.Resources;
import wang.bannong.gk5.sparrow.model.entity.Resource;
import wang.bannong.gk5.sparrow.service.IResourceService;

@Service
public class ScanMappings {

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    private String[] emptyArray = new String[]{""};

    /**
     * 扫描资源插入数据库
     */
    @PostConstruct
    public void doScan() {
        resourceService.saveOrUpdateBatch(
                handlerMapping.getHandlerMethods()
                        .values()
                        .stream()
                        .map(this::getResources)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 获取Resource
     *
     * @param handlerMethod
     * @return
     */
    public List<Resource> getResources(HandlerMethod handlerMethod) {
        Resources res = handlerMethod.getMethodAnnotation(Resources.class);
        if (Objects.isNull(res)) {
            return Collections.emptyList();
        }
        RequestMapping requestMappingAnnotation = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
        RequestMapping methodMappingAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
        if (Objects.isNull(requestMappingAnnotation) && Objects.isNull(methodMappingAnnotation)) {
            return Collections.emptyList();
        }
        ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
        String[] requestMappings = Objects.nonNull(requestMappingAnnotation) ? requestMappingAnnotation.value() : emptyArray;
        String[] methodMappings = Objects.nonNull(methodMappingAnnotation) ? methodMappingAnnotation.path() : emptyArray;
        RequestMethod[] method = Objects.nonNull(methodMappingAnnotation) ? methodMappingAnnotation.method() : new RequestMethod[0];
        requestMappings = ArrayUtils.isEmpty(requestMappings) ? emptyArray : requestMappings;
        methodMappings = ArrayUtils.isEmpty(methodMappings) ? emptyArray : methodMappings;
        Set<String> mappings = new HashSet<>(1);
        for (String reqMapping : requestMappings) {
            for (String methodMapping : methodMappings) {
                mappings.add(reqMapping + methodMapping);
            }
        }
        List<Resource> resources = new ArrayList<>(1);
        for (RequestMethod requestMethod : method) {
            for (String mapping : mappings) {
                //接口描述
                Resource resource = new Resource();
                resource.setResourceName(Objects.nonNull(apiOperation) ? apiOperation.value() : "未命名资源路径");
                resource.setMapping(mapping);
                resource.setMethod(requestMethod.name());
                resource.setAuthType(res.auth());
                resource.setPerm(resourceService.getResourcePermTag(requestMethod.name(), mapping));
                resource.setId(MD5Util.computeMD5(resource.getPerm()));
                resources.add(resource);
            }
        }
        return resources;
    }

}
