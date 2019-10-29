package wang.bannong.gk5.mini.sparrow.sample.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import wang.bannong.gk5.mini.sparrow.common.utils.TypeUtils;
import wang.bannong.gk5.mini.sparrow.sample.mapper.MenuMapper;
import wang.bannong.gk5.mini.sparrow.enums.MenuTypeEnum;
import wang.bannong.gk5.mini.sparrow.enums.StatusEnum;
import wang.bannong.gk5.mini.sparrow.framework.enums.ErrorCodeEnum;
import wang.bannong.gk5.mini.sparrow.framework.service.impl.BaseServiceImpl;
import wang.bannong.gk5.mini.sparrow.framework.utils.ApiAssert;
import wang.bannong.gk5.mini.sparrow.framework.utils.TreeUtils;
import wang.bannong.gk5.mini.sparrow.model.dto.MenuDTO;
import wang.bannong.gk5.mini.sparrow.model.dto.MenuTreeDTO;
import wang.bannong.gk5.mini.sparrow.model.entity.Menu;
import wang.bannong.gk5.mini.sparrow.model.entity.MenuResource;
import wang.bannong.gk5.mini.sparrow.service.IMenuResourceService;
import wang.bannong.gk5.mini.sparrow.service.IMenuService;

/**
 * 菜单表 服务实现类
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IMenuResourceService menuResourceService;

    @Override
    @Transactional
    public void saveMenu(Menu menu, List<String> resourceIds) {
        save(menu);
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            Integer menuId = menu.getId();
            //添加resource关联
            menuResourceService.saveBatch(menuResourceService.getMenuResources(menuId, resourceIds)
            );
        }
    }

    @Override
    @Transactional
    public void updateMenu(Menu menu, List<String> resourceIds) {
        updateById(menu);
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            Integer menuId = menu.getId();
            //删除resource关联
            menuResourceService.removeByMenuId(menuId);
            //添加resource关联
            menuResourceService.saveBatch(menuResourceService.getMenuResources(menuId, resourceIds)
            );
        }
    }

    @Override
    @Transactional
    public void removeMenu(Integer menuId) {
        if (parentIdNotNull(menuId)) {
            query().eq(Menu::getParentId, menuId)
                    .list()
                    .stream()
                    .filter(e -> parentIdNotNull(e.getParentId()))
                    .forEach(e -> removeMenu(e.getId()));
            //删除resource关联
            menuResourceService.removeByMenuId(menuId);
            //删除菜单
            removeById(menuId);
        }

    }

    @Override
    @Transactional
    public void updateStatus(Integer menuId, StatusEnum status) {
        Menu menu = getById(menuId);
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND, menu);
        menu.setStatus(status);
        updateById(menu);
    }

    /**
     * 父ID不为0并且不为空
     *
     * @param parentId
     * @return
     */
    private boolean parentIdNotNull(Integer parentId) {
        return Objects.nonNull(parentId) && parentId != 0;
    }

    @Override
    public MenuDTO getMenuDTODetails(Integer menuId) {
        Menu menu = getById(menuId);
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND, menu);
        MenuDTO menuDTO = menu.convert(MenuDTO.class);
        List<String> resourceIds = menuResourceService.query()
                .select(MenuResource::getResourceId)
                .eq(MenuResource::getMenuId, menuId)
                .listObjs(TypeUtils::castToString);
        menuDTO.setResourceIds(resourceIds);
        return menuDTO;
    }

    @Override
    public List<MenuTreeDTO> getUserPermMenus(Integer uid) {
        List<MenuTreeDTO> menus = baseMapper.getUserPermMenus(uid, StatusEnum.NORMAL, Arrays.asList(MenuTypeEnum.CATALOG, MenuTypeEnum.MENU));
        return menus.stream().filter(e -> !parentIdNotNull(e.getParentId())).map(e -> TreeUtils.findChildren(e, menus)).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUserPermButtonAliases(Integer uid) {
        return baseMapper.getUserPermMenus(uid, StatusEnum.NORMAL, Collections.singletonList(MenuTypeEnum.BUTTON))
                .stream()
                .map(MenuTreeDTO::getAlias)
                .collect(Collectors.toSet());
    }
}
