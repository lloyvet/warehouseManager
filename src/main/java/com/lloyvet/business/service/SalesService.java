package com.lloyvet.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.business.domain.Sales;
import com.lloyvet.business.vo.SalesVo;
import com.lloyvet.system.common.DataGridView;


public interface SalesService extends IService<Sales>{


    DataGridView queryAllSales(SalesVo salesVo);

    Sales saveSales(Sales sales);

    Sales updateSales(Sales sales);
}
