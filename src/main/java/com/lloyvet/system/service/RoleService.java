package com.lloyvet.system.service;

import com.lloyvet.system.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.system.vo.RoleVo;

import java.io.Serializable;
import java.util.List;

public interface RoleService extends IService<Role>{


    Object queryAllRole(RoleVo roleVo);

    Role updateRole(Role role);

    Role saveRole(Role role);


    List<Integer> queryMenuIdsByRid(Integer id);

    void saveRoleMenu(Integer rid, Integer[] mids);
}
