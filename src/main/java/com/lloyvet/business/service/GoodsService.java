package com.lloyvet.business.service;

import com.lloyvet.business.domain.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.business.vo.GoodsVo;
import com.lloyvet.system.common.DataGridView;

import java.util.List;

public interface GoodsService extends IService<Goods>{


    DataGridView queryAllGoods(GoodsVo goodsVo);

    Goods saveGoods(Goods goods);

    Goods updateGoods(Goods goods);

    DataGridView queryAllAvailableGoods();

    List<Goods> getGoodsByProviderId(Integer id);
}
