package com.lloyvet.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.system.mapper.DeptMapper;
import com.lloyvet.system.domain.Dept;
import com.lloyvet.system.service.DeptService;
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public DataGridView queryAllDept(DeptVo deptVo) {
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        qw.orderByDesc("ordernum");
        List<Dept> depts = deptMapper.selectList(qw);

        return new DataGridView(Long.valueOf(depts.size()),depts);
    }

    @Override
    public Dept saveDept(Dept dept) {
        deptMapper.insert(dept);
        return dept;
    }

    @Override
    public Integer queryDeptMaxOrderNum() {
        return deptMapper.queryDeptMaxOrderNum();
    }

    @Override
    public Integer countChildren(Integer id) {
        return deptMapper.selectCountChildren(id);
    }

    @Override
    public Dept updateDept(Dept dept) {
        deptMapper.updateById(dept);
        return dept;
    }
}
