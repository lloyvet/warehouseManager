package com.lloyvet.business.controller;


import com.lloyvet.business.domain.Salesback;
import com.lloyvet.business.service.SalesbackService;
import com.lloyvet.business.vo.SalesbackVo;
import com.lloyvet.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("salesback")
@RestController
public class SalesBackController {

    @Autowired
    private SalesbackService salesbackService;


    /**
     * 查询
     */
    @RequestMapping("loadAllSalesback")
    public Object loadAllSalesback(SalesbackVo salesbackVo){
        return this.salesbackService.queryAllSalesback(salesbackVo);
    }

    /**
     * 添加退货信息
     */
    @RequestMapping("addSalesback")
    public ResultObj addSalesback(Salesback salesback){
        try {
            this.salesbackService.saveSalesback(salesback);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
}
