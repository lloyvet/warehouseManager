package com.lloyvet.business.service;

import com.lloyvet.business.domain.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.business.vo.CustomerVo;
import com.lloyvet.system.common.DataGridView;

public interface CustomerService extends IService<Customer>{


    DataGridView queryAllCustomer(CustomerVo customerVo);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
