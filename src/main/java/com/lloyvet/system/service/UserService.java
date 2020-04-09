package com.lloyvet.system.service;

import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.system.vo.UserVo;

public interface UserService extends IService<User>{

    /**
     * 根据用户登录名查询用户信息
     */
    User queryUserByLoginName(String loginName);

    User saveUser(User user);

    DataGridView queryAllUser(UserVo userVo);

    User updateUser(User user);

    Integer queryMenuMaxOrderNum();

    void saveUserRole(Integer uid, Integer[] rids);
}
