package com.lloyvet.business.controller;

import com.lloyvet.business.domain.Goods;
import com.lloyvet.business.service.GoodsService;
import com.lloyvet.business.vo.GoodsVo;
import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询
     */
    @RequestMapping("loadAllGoods")
    public Object loadAllGoods(GoodsVo goodsVo){
        return goodsService.queryAllGoods(goodsVo);
    }
    /**
     * 添加
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(Goods goods){
        try{
            goods.setAvailable(Constant.AVAILABLE_TRUE);
            goodsService.saveGoods(goods);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(Goods goods){
        try{
            goodsService.updateGoods(goods);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     */
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Goods goods){
        try {
            goodsService.removeById(goods);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除
     */
    @RequestMapping("batchDeleteGoods")
    public ResultObj batchDeleteGoods(Integer[] ids){
        try {
            goodsService.removeByIds(Arrays.asList(ids));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /***
     * 查询所有的商品不分页
     */
    @RequestMapping("getAllAvailableGoods")
    public Object getAllAvailableGoods(){
        return goodsService.queryAllAvailableGoods();
    }
    /**
     * 根据供应商id查询商品
     */
    @RequestMapping("getGoodsByProviderId")
    public Object getGoodsByProviderId( Integer providerid){
        List<Goods> goods = goodsService.getGoodsByProviderId(providerid);
        return new DataGridView(goods);
    }
    @RequestMapping("getGoodsByGoodId")
    public Object getGoodsByGoodId(Integer goodsid){
        return new DataGridView(goodsService.getById(goodsid));
    }
}
