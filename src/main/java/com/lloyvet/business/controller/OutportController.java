package com.lloyvet.business.controller;


import com.lloyvet.business.domain.Outport;
import com.lloyvet.business.service.OutportService;
import com.lloyvet.business.vo.OutportVo;
import com.lloyvet.system.common.ActiveUser;
import com.lloyvet.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RequestMapping("outport")
@RestController
public class OutportController {

    @Autowired
    private OutportService outportService;


    /**
     * 查询
     */
    @RequestMapping("loadAllOutport")
    public Object loadAllOutport(OutportVo outportVo){
        return this.outportService.queryAllOutport(outportVo);
    }

    /**
     * 添加
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Outport outport){
        try {
            ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            outport.setOperateperson(activeUser.getUser().getName());
            outport.setOutporttime(new Date());
            this.outportService.saveOutport(outport);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("updateOutport")
//    public ResultObj updateOutport(Outport outport){
//        try {
//            this.outportService.updateOutport(outport);
//            return ResultObj.UPDATE_SUCCESS;
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            return ResultObj.UPDATE_ERROR;
//        }
//
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("deleteOutport")
//    public ResultObj deleteOutport(Integer id){
//        try {
//            this.outportService.removeById(id);
//            return ResultObj.DELETE_SUCCESS;
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            return ResultObj.DELETE_ERROR;
//        }
//    }
}
