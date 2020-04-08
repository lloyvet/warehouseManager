package com.lloyvet.system.controller;

import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.common.ResultObj;
import com.lloyvet.system.domain.Menu;
import com.lloyvet.system.service.MenuService;
import com.lloyvet.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("loadAllMenu")
    public Object loadAllMenu(MenuVo menuVo){
        return menuService.queryAllMenu(menuVo);
    }

    /**
     * 查询菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("loadMenu")
    public Object loadMenu(MenuVo menuVo){
        List<Menu> menus = menuService.queryAllMenuForList();
        return new DataGridView(Long.valueOf(menus.size()),menus);
    }
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(Menu menu){
        try{
            menuService.updateMenu(menu);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 添加菜单
     */
    @PostMapping("addMenu")
    public ResultObj addMenu(Menu menu){
        try{
            if(menu.getType().equals("topmenu")){
                menu.setPid(0);
            }
            menu.setSpread(Constant.SPREAD_FALSE);
            menu.setAvailable(Constant.AVAILABLE_TRUE);
            menuService.saveMenu(menu);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 查询当前菜单是否有子菜单
     */
    @RequestMapping("getMenuChildrenCountById")
    public DataGridView getMenuChildrenCountById(Integer id){
        Integer integer = menuService.countChildren(id);
        return new DataGridView(Long.valueOf(integer));
    }
    /**
     * 删除菜单
     */
    @PostMapping("deleteMenu")
    public ResultObj deleteMenu(Integer id){
        try{
            menuService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @GetMapping("getMenuById")
    public Object getMenuById(Integer id){
        return new DataGridView(menuService.getById(id));
    }
    /**
     * 加载排序码
     */
    @GetMapping("queryMenuMaxOrderNum")
    public DataGridView queryMenuMaxOrderNum(){
        Integer num = menuService.queryMenuMaxOrderNum();
        return new DataGridView(Long.valueOf(num+1));
    }
}
