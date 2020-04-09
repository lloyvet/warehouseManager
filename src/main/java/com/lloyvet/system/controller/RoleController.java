package com.lloyvet.system.controller;

import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.common.ResultObj;
import com.lloyvet.system.domain.Role;
import com.lloyvet.system.service.RoleService;
import com.lloyvet.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("loadAllRole")
    public Object loadAllRole(RoleVo roleVo){
        return roleService.queryAllRole(roleVo);
    }
    @RequestMapping("updateRole")
    public ResultObj updateRole(Role role){
        try{
            roleService.updateRole(role);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加角色
     */
    @PostMapping("addRole")
    public ResultObj addRole(Role role){
        try{
            role.setCreatetime(new Date());
            role.setAvailable(Constant.AVAILABLE_TRUE);
            roleService.saveRole(role);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除角色
     */
    @PostMapping("deleteRole")
    public ResultObj deleteRole(Integer id){
        try{
            roleService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除
     */
    @PostMapping("batchDeleteRole")
    public ResultObj batchDeleteRole(Integer[] ids){
        try{
            roleService.removeByIds(Arrays.asList(ids));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 查询所有可用角色
     * @param roleVo
     * @return
     */
    @RequestMapping("loadAllAvailableRoleNoPage")
    public Object loadAllAvailableRoleNoPage(RoleVo roleVo){
        roleVo.setAvailable(Constant.AVAILABLE_TRUE);
        return this.roleService.queryAllAvailableRoleNoPage(roleVo);
    }
    /**
     * 根据角色id查询角色拥有的菜单和权限id
     */
    @GetMapping("queryMenuIdsByRid")
    public Object queryMenuIdsByRid(Integer id){
        List<Integer> mids = roleService.queryMenuIdsByRid(id);
        return new DataGridView(mids);
    }
    /**
     * 根据角色id保存菜单权限之间的关系
     */
    @PostMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(Integer rid,Integer[] mids){
        try{
            roleService.saveRoleMenu(rid,mids);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
