package com.lloyvet.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.system.mapper.MenuMapper;
import com.lloyvet.system.domain.Menu;
import com.lloyvet.system.service.MenuService;
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> queryAllMenuForList() {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type",Constant.MENU_TYPE_TOP)
                        .or().eq("type",Constant.MENU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        return menuMapper.selectList(qw);
    }

    @Override
    public List<Menu> queryMenuForListByUserId(Integer id) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type",Constant.MENU_TYPE_TOP)
                        .or().eq("type",Constant.MENU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        return menuMapper.selectList(qw);
    }

    @Override
    public Integer countChildren(Integer id) {
        return menuMapper.countChildren(id);
    }

    @Override
    public Menu saveMenu(Menu menu) {
        menuMapper.insert(menu);
        return menu;
    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq(menuVo.getAvailable()!=null,"available",menuVo.getAvailable());
        qw.orderByAsc("ordernum");
        List<Menu> menus = menuMapper.selectList(qw);
        return new DataGridView(Long.valueOf(menus.size()),menus);
    }

    @Override
    public Menu updateMenu(Menu menu) {
        menuMapper.updateById(menu);
        return menu;
    }

    @Override
    public Integer queryMenuMaxOrderNum() {
        return menuMapper.queryMenuMaxOrderNum();
    }
}
