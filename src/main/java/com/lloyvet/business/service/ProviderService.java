package com.lloyvet.business.service;

import com.lloyvet.business.domain.Provider;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.business.vo.ProviderVo;
import com.lloyvet.system.common.DataGridView;

public interface ProviderService extends IService<Provider>{


    DataGridView queryAllProvider(ProviderVo providerVo);

    Provider saveProvider(Provider provider);

    Provider updateProvider(Provider provider);

    DataGridView getAllAvailableProvider();
}
