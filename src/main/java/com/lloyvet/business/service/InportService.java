package com.lloyvet.business.service;

import com.lloyvet.business.domain.Inport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.business.vo.InportVo;
import com.lloyvet.system.common.DataGridView;

public interface InportService extends IService<Inport>{
    Inport saveInport(Inport inport);

    Inport updateInport(Inport inport);

    DataGridView queryAllInport(InportVo inportVo);

}
