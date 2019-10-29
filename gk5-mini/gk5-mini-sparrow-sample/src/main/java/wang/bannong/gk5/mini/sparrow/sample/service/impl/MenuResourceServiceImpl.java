package wang.bannong.gk5.mini.sparrow.sample.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import wang.bannong.gk5.mini.sparrow.sample.mapper.MenuResourceMapper;
import wang.bannong.gk5.mini.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.mini.sparrow.model.entity.MenuResource;
import wang.bannong.gk5.mini.sparrow.service.IMenuResourceService;

@Service
public class MenuResourceServiceImpl extends BaseServiceImpl<MenuResourceMapper, MenuResource> implements IMenuResourceService {

    @Override
    public void removeByMenuId(Integer menuId) {
        delete().eq(MenuResource::getMenuId, menuId).execute();
    }

    @Override
    public List<MenuResource> getMenuResources(Integer menuId, List<String> resourceIds) {
        return resourceIds.stream().map(resourceId -> new MenuResource(menuId, resourceId)).collect(Collectors.toList());
    }

}
