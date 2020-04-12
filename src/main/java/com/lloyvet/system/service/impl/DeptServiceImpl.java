package com.lloyvet.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
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
    @CachePut(cacheNames = "com.lloyvet.system.service.impl.DeptServiceImpl",key = "#result.id")
    @Override
    public Dept saveDept(Dept dept) {
        Dept selectById = this.deptMapper.selectById(dept.getId());
        BeanUtil.copyProperties(dept,selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        this.deptMapper.updateById(selectById);
        return selectById;
    }

    @Override
    public Integer queryDeptMaxOrderNum() {
        return deptMapper.queryDeptMaxOrderNum();
    }

    @Cacheable(cacheNames = "com.lloyvet.system.service.impl.DeptServiceImpl",key = "#id")
    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public Integer countChildren(Integer id) {
        return deptMapper.selectCountChildren(id);
    }
    @CachePut(cacheNames = "com.lloyvet.system.service.impl.DeptServiceImpl",key = "#result.id")
    @Override
    public Dept updateDept(Dept dept) {
        deptMapper.updateById(dept);
        return dept;
    }

    @CacheEvict(cacheNames = "com.lloyvet.system.service.impl.DeptServiceImpl",key = "#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
