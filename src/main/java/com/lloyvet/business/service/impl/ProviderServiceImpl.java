package com.lloyvet.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.business.vo.ProviderVo;
import com.lloyvet.system.common.DataGridView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.business.mapper.ProviderMapper;
import com.lloyvet.business.domain.Provider;
import com.lloyvet.business.service.ProviderService;
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService{

    @Autowired
    private ProviderMapper providerMapper;
    @Override
    public DataGridView queryAllProvider(ProviderVo providerVo) {
        IPage<Provider> page = new Page<>(providerVo.getPage(),providerVo.getLimit());
        QueryWrapper<Provider> qw = new QueryWrapper<>();
        qw.eq(providerVo.getAvailable()!=null,"available",providerVo.getAvailable());
        qw.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        qw.like(StringUtils.isNotBlank(providerVo.getConnectionperson()),"connectionperson",providerVo.getConnectionperson());
        if(StringUtils.isNotBlank(providerVo.getPhone())){
            qw.and(new Consumer<QueryWrapper<Provider>>() {
                @Override
                public void accept(QueryWrapper<Provider> providerQueryWrapper) {
                    providerQueryWrapper.like(StringUtils.isNotBlank(providerVo.getPhone()),"phone",providerVo.getPhone())
                            .or().like(StringUtils.isNotBlank(providerVo.getPhone()),"telephone",providerVo.getPhone());
                }
            });
        }
        providerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    @CachePut(cacheNames = "com.lloyvet.business.service.impl.ProviderServiceImpl",key = "#result.id")
    @Override
    public Provider saveProvider(Provider provider) {
        providerMapper.insert(provider);
        return provider;
    }
    @CachePut(cacheNames = "com.lloyvet.business.service.impl.ProviderServiceImpl",key = "#result.id")
    @Override
    public Provider updateProvider(Provider provider) {
        Provider selectById = providerMapper.selectById(provider.getId());
        BeanUtil.copyProperties(provider,selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        providerMapper.updateById(selectById);
        return selectById;
    }

    @Override
    public DataGridView getAllAvailableProvider() {
        List<Provider> providers = providerMapper.getAllAvailableProvider();
        return new DataGridView(providers);
    }

    @CacheEvict(cacheNames = "com.lloyvet.business.service.impl.ProviderServiceImpl",key = "#result.id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @CacheEvict(cacheNames = "com.lloyvet.business.service.impl.ProviderServiceImpl",key = "#id")
    @Override
    public Provider getById(Serializable id) {
        return super.getById(id);
    }
}
