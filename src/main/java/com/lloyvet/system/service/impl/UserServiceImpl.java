package com.lloyvet.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.system.mapper.UserMapper;
import com.lloyvet.system.domain.User;
import com.lloyvet.system.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    private Log log = LogFactory.getLog(UserService.class);
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByLoginName(String loginName) {

        QueryWrapper<User> qw = new QueryWrapper<>();
        if(StringUtils.isBlank(loginName)){
            log.error("登录名不等为空");
            return null;
        }
        qw.eq("loginname",loginName);
        User user = userMapper.selectOne(qw);
        return user;
    }
}
