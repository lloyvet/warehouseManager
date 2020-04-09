package com.lloyvet.system.service;

import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.system.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu>{
    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> queryAllMenuForList();

    /**
     * g根据用户id查询菜单
     * @param id
     * @return
     */
    List<Menu> queryMenuForListByUserId(Integer id);

    Integer countChildren(Integer id);

    Menu saveMenu(Menu menu);

    DataGridView queryAllMenu(MenuVo menuVo);

    Menu updateMenu(Menu menu);

    Integer queryMenuMaxOrderNum();

    List<String> queryPermissionCodeByUserId(Integer id);
}
