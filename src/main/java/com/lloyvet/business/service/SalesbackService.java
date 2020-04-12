package com.lloyvet.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.business.domain.Salesback;
import com.lloyvet.business.vo.SalesbackVo;
import com.lloyvet.system.common.DataGridView;


public interface SalesbackService extends IService<Salesback>{


    Salesback saveSalesback(Salesback salesback);

    DataGridView queryAllSalesback(SalesbackVo salesbackVo);
}
