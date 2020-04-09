package com.lloyvet.system.controller;

import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.common.ResultObj;
import com.lloyvet.system.domain.Dept;
import com.lloyvet.system.service.DeptService;
import com.lloyvet.system.vo.DeptVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("loadAllDept")
    public Object loadAllDept(DeptVo deptVo){
        return deptService.queryAllDept(deptVo);
    }
    @RequestMapping("updateDept")
    @RequiresPermissions("dept:update")
    public ResultObj updateDept(Dept dept){
        try{
            deptService.updateDept(dept);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 查询部门排序码
     */
    @GetMapping("queryDeptMaxOrderNum")
    public Object queryDeptMaxOrderNum(){
        Integer maxValue = deptService.queryDeptMaxOrderNum();
        return new DataGridView(maxValue+1);
    }
    /**
     * 添加部门
     */
    @PostMapping("addDept")
    @RequiresPermissions("dept:add")
    public ResultObj addDept(Dept dept){
        try{
            dept.setSpread(Constant.SPREAD_FALSE);
            dept.setAvailable(Constant.AVAILABLE_TRUE);
            deptService.saveDept(dept);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 查询当前部门是否有子部门
     */
    @GetMapping("getDeptChildrenCountById")
    public DataGridView getDeptChildrenCountById(Integer id){
        Integer integer = deptService.countChildren(id);
        return new DataGridView(Long.valueOf(integer));
    }
    /**
     * 删除部门
     */
    @PostMapping("deleteDept")
    @RequiresPermissions("dept:delete")
    public ResultObj deleteDept(Integer id){
        try{
            deptService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @GetMapping("getDeptById")
    public Object getDeptById(Integer id){
        return new DataGridView(deptService.getById(id));
    }
}
