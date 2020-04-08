package com.lloyvet.system.service;

import com.lloyvet.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
public interface UserService extends IService<User>{

    /**
     * 根据用户登录名查询用户信息
     */
    User queryUserByLoginName(String loginName);

}
