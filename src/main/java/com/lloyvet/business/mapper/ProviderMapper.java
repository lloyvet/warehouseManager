package com.lloyvet.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.business.domain.Provider;

import java.util.List;

public interface ProviderMapper extends BaseMapper<Provider> {
    List<Provider> getAllAvailableProvider();
}