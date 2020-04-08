package com.lloyvet.system.service;

import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.domain.Loginfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.system.vo.LoginfoVo;

public interface LoginfoService extends IService<Loginfo>{


    DataGridView queryAllLoginfo(LoginfoVo loginfoVo);
}
