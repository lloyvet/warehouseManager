package com.lloyvet.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.system.mapper.RoleMapper;
import com.lloyvet.system.domain.Role;
import com.lloyvet.system.service.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        IPage<Role> page = new Page<>(roleVo.getPage(),roleVo.getLimit());
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        qw.like(StringUtils.isNotBlank(roleVo.getName()),"name",roleVo.getName());
        qw.eq(StringUtils.isNotBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        roleMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public Role updateRole(Role role) {
        roleMapper.updateById(role);
        return role;
    }

    @Override
    public Role saveRole(Role role) {
        roleMapper.insert(role);
        return role;
    }

    @Override
    public List<Integer> queryMenuIdsByRid(Integer id) {
        return roleMapper.queryMenuIdsByRid(id);
    }

    /**
     * 保存角色和菜单之间的关系
     * @param rid
     * @param mids
     */
    @Override
    public void saveRoleMenu(Integer rid, Integer[] mids) {
        //根据rid删除sys_role_menu里面的数据
        roleMapper.deleteRoleMenuByRid(rid);
        if(null!=mids&&mids.length>0){
            for (Integer mid : mids) {
                roleMapper.insertRoleMenu(rid,mid);
            }
        }
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据角色ID删除角色和菜单之间的关系
        roleMapper.deleteRoleMenuByRid(id);
        //根据角色ID删除角色和用户之间的关系
        roleMapper.deleteRoleUserByRid(id);

        return super.removeById(id);
    }

}
