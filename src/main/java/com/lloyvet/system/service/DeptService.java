package com.lloyvet.system.service;

import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.system.vo.DeptVo;

public interface DeptService extends IService<Dept>{


    DataGridView queryAllDept(DeptVo deptVo);

    Dept saveDept(Dept dept);

    Integer queryDeptMaxOrderNum();

    Integer countChildren(Integer id);

    Dept updateDept(Dept dept);
}
