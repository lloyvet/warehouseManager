package com.lloyvet.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.business.vo.CustomerVo;
import com.lloyvet.system.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.business.mapper.CustomerMapper;
import com.lloyvet.business.domain.Customer;
import com.lloyvet.business.service.CustomerService;
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        IPage<Customer> page = new Page<>(customerVo.getPage(),customerVo.getLimit());
        QueryWrapper<Customer> qw = new QueryWrapper<>();
        qw.eq(customerVo.getAvailable()!=null,"available",customerVo.getAvailable());
        qw.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        qw.like(StringUtils.isNotBlank(customerVo.getConnectionperson()),"connectionperson",customerVo.getConnectionperson());
        if(StringUtils.isNotBlank(customerVo.getPhone())){
            qw.and(new Consumer<QueryWrapper<Customer>>() {
                @Override
                public void accept(QueryWrapper<Customer> customerQueryWrapper) {
                    customerQueryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()),"phone",customerVo.getPhone())
                            .or().like(StringUtils.isNotBlank(customerVo.getPhone()),"telephone",customerVo.getPhone());
                }
            });
        }
        customerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customerMapper.insert(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customerMapper.updateById(customer);
        return customer;
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
