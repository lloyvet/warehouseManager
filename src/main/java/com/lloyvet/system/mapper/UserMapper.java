package com.lloyvet.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.system.domain.User;

public interface UserMapper extends BaseMapper<User> {
    Integer queryMenuMaxOrderNum();

    void saveUserRole(Integer uid, Integer rid);
}