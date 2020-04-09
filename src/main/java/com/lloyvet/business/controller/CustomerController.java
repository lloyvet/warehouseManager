package com.lloyvet.business.controller;

import com.lloyvet.business.domain.Customer;
import com.lloyvet.business.service.CustomerService;
import com.lloyvet.business.vo.CustomerVo;
import com.lloyvet.system.common.ActiveUser;
import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 查询
     */
    @RequestMapping("loadAllCustomer")
    public Object loadAllCustomer(CustomerVo customerVo){
        return customerService.queryAllCustomer(customerVo);
    }
    /**
     * 添加
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(Customer customer){
        try{
            customer.setAvailable(Constant.AVAILABLE_TRUE);
            customerService.saveCustomer(customer);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(Customer customer){
        try{
            customerService.updateCustomer(customer);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     */
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(Customer customer){
        try {
            customerService.removeById(customer);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除
     */
    @RequestMapping("batchDeleteCustomer")
    public ResultObj batchDeleteCustomer(Integer[] ids){
        try {
            customerService.removeByIds(Arrays.asList(ids));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
