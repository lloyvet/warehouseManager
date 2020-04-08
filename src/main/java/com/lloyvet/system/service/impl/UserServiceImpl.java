package com.lloyvet.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.system.common.AppUtils;
import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.domain.Dept;
import com.lloyvet.system.mapper.DeptMapper;
import com.lloyvet.system.service.DeptService;
import com.lloyvet.system.vo.UserVo;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.system.mapper.UserMapper;
import com.lloyvet.system.domain.User;
import com.lloyvet.system.service.UserService;

import java.util.List;

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

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        IPage<User> page = new Page<>(userVo.getPage(),userVo.getLimit());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("type", Constant.USER_TYPE_NORMAL);
        qw.eq(null!=userVo.getDeptid(),"deptid",userVo.getDeptid());
        qw.like(StringUtils.isNotBlank(userVo.getName()),"name",userVo.getName());
        qw.like(StringUtils.isNotBlank(userVo.getAddress()),"name",userVo.getAddress());
        qw.like(StringUtils.isNotBlank(userVo.getRemark()),"name",userVo.getRemark());
        userMapper.selectPage(page,qw);
        List<User> records = page.getRecords();
        DeptService deptService = AppUtils.getContext().getBean(DeptService.class);
        for (User record : records) {
            if(null!=record.getDeptid()){
                Dept dept = deptService.getById(record.getDeptid());
                record.setDeptname(dept.getTitle());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
