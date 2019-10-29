package wang.bannong.gk5.mini.sparrow.sample.service.impl;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import wang.bannong.gk5.mini.sparrow.sample.mapper.ResourceMapper;
import wang.bannong.gk5.mini.sparrow.enums.AuthTypeEnum;
import wang.bannong.gk5.mini.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.mini.sparrow.model.dto.ResourcePermDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Resource;
import wang.bannong.gk5.mini.sparrow.service.IResourceService;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public List<String> getUserPerms(Integer uid) {
        return getUserResourcePerms(uid).stream().map(e -> this.getResourcePermTag(e.getMethod(), e.getMapping())).collect(Collectors.toList());
    }

    @Override
    public String getResourcePermTag(String method, String mapping) {
        return method + ":" + mapping;
    }

    @Override
    public Set<ResourcePermDTO> getUserResourcePerms(Integer uid) {
        List<ResourcePermDTO> perms = getPerms(AuthTypeEnum.OPEN, AuthTypeEnum.LOGIN);
        List<ResourcePermDTO> resourcePerms = baseMapper.getUserResourcePerms(uid);
        List<ResourcePermDTO> userMenuResourcePerms = getUserMenuResourcePerms(uid);
        perms.addAll(resourcePerms);
        perms.addAll(userMenuResourcePerms);
        return new HashSet<>(perms);
    }

    @Override
    public List<ResourcePermDTO> getUserMenuResourcePerms(Integer uid) {
        return baseMapper.getUserMenuResourcePerms(uid);
    }

    @Override
    @Transactional
    public boolean saveOrUpdateBatch(Collection<Resource> entityList) {
        //批量对象插入 不存在直接返回true
        if (CollectionUtils.isEmpty(entityList)) {
            return true;
        }
        Map<String, Resource> resourceMap = list2Map(Resource::getId);
        int i = 0;
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (Resource entity : entityList) {
                String idVal = entity.getId();
                Resource resource = resourceMap.get(idVal);
                if (Objects.nonNull(resource)) {
                    entity.setUpdateTime(resource.getUpdateTime());
                    MapperMethod.ParamMap<Resource> param = new MapperMethod.ParamMap<>();
                    param.put(Constants.ENTITY, entity);
                    batchSqlSession.update(sqlStatement(SqlMethod.UPDATE_BY_ID), param);
                } else {
                    batchSqlSession.insert(sqlStatement(SqlMethod.INSERT_ONE), entity);
                }
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    @Override
    public List<ResourcePermDTO> getOpenPerms() {
        return getPerms(AuthTypeEnum.OPEN);
    }

    @Override
    public List<ResourcePermDTO> getLoginPerms() {
        return getPerms(AuthTypeEnum.LOGIN);
    }

    @Override
    public List<ResourcePermDTO> getPerms(AuthTypeEnum... authTypes) {
        return query().select(Resource::getMethod, Resource::getMapping).in(ArrayUtils.isNotEmpty(authTypes), Resource::getAuthType, (Object[]) authTypes).entitys(e -> e.convert(ResourcePermDTO.class));
    }

    @Override
    public List<ResourcePermDTO> getPerms() {
        return getPerms((AuthTypeEnum[]) null);
    }

    @Override
    public List<ResourcePermDTO> getResourcePerms(String method) {
        return query().select(Resource::getMethod, Resource::getMapping).eq(Resource::getMethod, method).entitys(e -> e.convert(ResourcePermDTO.class));
    }

}
