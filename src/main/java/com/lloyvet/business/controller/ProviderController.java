package com.lloyvet.business.controller;

import com.lloyvet.business.domain.Provider;
import com.lloyvet.business.service.ProviderService;
import com.lloyvet.business.vo.ProviderVo;
import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /**
     * 查询
     */
    @RequestMapping("loadAllProvider")
    public Object loadAllProvider(ProviderVo providerVo){
        return providerService.queryAllProvider(providerVo);
    }
    /**
     * 添加
     */
    @RequestMapping("addProvider")
    public ResultObj addProvider(Provider provider){
        try{
            provider.setAvailable(Constant.AVAILABLE_TRUE);
            providerService.saveProvider(provider);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改
     */
    @RequestMapping("updateProvider")
    public ResultObj updateProvider(Provider provider){
        try{
            providerService.updateProvider(provider);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     */
    @RequestMapping("deleteProvider")
    public ResultObj deleteProvider(Provider provider){
        try {
            providerService.removeById(provider);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除
     */
    @RequestMapping("batchDeleteProvider")
    public ResultObj batchDeleteProvider(Integer[] ids){
        try {
            providerService.removeByIds(Arrays.asList(ids));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 查询所有可用的供应商
     */
    @GetMapping("getAllAvailableProvider")
    public DataGridView getAllAvailableProvider(){
        return providerService.getAllAvailableProvider();
    }
}
