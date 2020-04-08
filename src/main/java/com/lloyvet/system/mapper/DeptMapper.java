package com.lloyvet.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.system.domain.Dept;

public interface DeptMapper extends BaseMapper<Dept> {
    Integer queryDeptMaxOrderNum();

    Integer selectCountChildren(Integer id);
}