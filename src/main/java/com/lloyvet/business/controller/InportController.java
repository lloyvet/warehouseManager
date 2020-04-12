package com.lloyvet.business.controller;


import com.lloyvet.business.domain.Inport;
import com.lloyvet.business.service.InportService;
import com.lloyvet.business.vo.InportVo;
import com.lloyvet.system.common.ActiveUser;
import com.lloyvet.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RequestMapping("inport")
@RestController
public class InportController {

    @Autowired
    private InportService inportService;


    /**
     * 查询
     */
    @RequestMapping("loadAllInport")
    public Object loadAllInport(InportVo inportVo){
        return this.inportService.queryAllInport(inportVo);
    }

    /**
     * 添加
     */
    @RequestMapping("addInport")
    public ResultObj addInport(Inport inport){
        try {
            ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            inport.setOperateperson(activeUser.getUser().getName());
            inport.setInporttime(new Date());
            this.inportService.saveInport(inport);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    /**
     * 修改
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(Inport inport){
        try {
            this.inportService.updateInport(inport);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    /**
     * 删除
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id){
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
