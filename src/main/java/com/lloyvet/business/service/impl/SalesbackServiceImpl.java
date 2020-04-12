package com.lloyvet.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lloyvet.business.domain.Customer;
import com.lloyvet.business.domain.Goods;
import com.lloyvet.business.domain.Sales;
import com.lloyvet.business.domain.Salesback;
import com.lloyvet.business.mapper.SalesMapper;
import com.lloyvet.business.mapper.SalesbackMapper;
import com.lloyvet.business.service.CustomerService;
import com.lloyvet.business.service.GoodsService;
import com.lloyvet.business.service.SalesbackService;
import com.lloyvet.business.vo.SalesbackVo;
import com.lloyvet.system.common.ActiveUser;
import com.lloyvet.system.common.DataGridView;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesbackServiceImpl extends ServiceImpl<SalesbackMapper, Salesback> implements SalesbackService {

    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private SalesbackMapper salesbackMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomerService customerService;


    @Override
    public Salesback saveSalesback(Salesback salesback) {
        Integer salesid=salesback.getSalesid();
        Sales sales=this.salesMapper.selectById(salesid);
        salesback.setGoodsid(sales.getGoodsid());
        salesback.setPaytype(sales.getPaytype());
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        salesback.setSalesbacktime(new Date());
        salesback.setSalebackprice(sales.getSaleprice());
        salesback.setOperateperson(activeUser.getUser().getName());
        salesback.setCustomerid(sales.getCustomerid());

        //保存退货信息
        this.salesbackMapper.insert(salesback);

        //增加库存
        Goods goods=this.goodsService.getById(sales.getGoodsid());
        goods.setNumber(goods.getNumber()+salesback.getNumber());
        this.goodsService.updateGoods(goods);

        //更新销售单信息
        sales.setNumber(sales.getNumber()-salesback.getNumber());
        salesMapper.updateById(sales);

        return salesback;
    }

    @Override
    public DataGridView queryAllSalesback(SalesbackVo salesbackVo) {
        IPage<Salesback> page=new Page<>(salesbackVo.getPage(),salesbackVo.getLimit());
        QueryWrapper<Salesback> qw=new QueryWrapper<>();
        qw.eq(salesbackVo.getGoodsid()!=null,"goodsid",salesbackVo.getGoodsid());
        qw.eq(salesbackVo.getCustomerid()!=null,"customerid",salesbackVo.getCustomerid());

        qw.ge(salesbackVo.getStartTime()!=null,"salesbacktime",salesbackVo.getStartTime());
        qw.le(salesbackVo.getEndTime()!=null,"salesbacktime",salesbackVo.getEndTime());

        qw.orderByDesc("salesbacktime");

        this.salesbackMapper.selectPage(page,qw);
        List<Salesback> records = page.getRecords();
        for (Salesback record : records) {
            if(null!=record.getGoodsid()){
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!= record.getCustomerid()){
                Customer customer = this.customerService.getById(record.getCustomerid());
                record.setCustomername(customer.getCustomername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }


}
