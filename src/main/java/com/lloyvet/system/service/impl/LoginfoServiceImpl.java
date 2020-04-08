package com.lloyvet.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.system.mapper.LoginfoMapper;
import com.lloyvet.system.domain.Loginfo;
import com.lloyvet.system.service.LoginfoService;
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService{

    @Autowired
    private LoginfoMapper loginfoMapper;
    @Override
    public DataGridView queryAllLoginfo(LoginfoVo loginfoVo) {
        IPage<Loginfo> page = new Page<>(loginfoVo.getPage(),loginfoVo.getLimit());
        QueryWrapper<Loginfo> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname",loginfoVo.getLoginname());
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginip()),"loginip",loginfoVo.getLoginip());
        qw.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        qw.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getEndTime());
        qw.orderByDesc("logintime");
        this.loginfoMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
}
