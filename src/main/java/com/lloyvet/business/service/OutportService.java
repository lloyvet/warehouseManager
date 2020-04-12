package com.lloyvet.business.service;

import com.lloyvet.business.domain.Outport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.business.vo.OutportVo;
import com.lloyvet.system.common.DataGridView;

public interface OutportService extends IService<Outport>{


    Outport saveOutport(Outport outport);

    DataGridView queryAllOutport(OutportVo outportVo);
}
