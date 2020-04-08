package com.lloyvet.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.system.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    void deleteRoleMenuByRid(Serializable id);

    void deleteRoleUserByRid(Serializable id);

    List<Integer> queryMenuIdsByRid(Integer id);

    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);
}