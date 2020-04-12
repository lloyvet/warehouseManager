package com.lloyvet.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.business.domain.Goods;
import com.lloyvet.business.domain.Provider;
import com.lloyvet.business.service.GoodsService;
import com.lloyvet.business.service.ProviderService;
import com.lloyvet.business.vo.InportVo;
import com.lloyvet.system.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.business.mapper.InportMapper;
import com.lloyvet.business.domain.Inport;
import com.lloyvet.business.service.InportService;
@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService{
    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public DataGridView queryAllInport(InportVo inportVo) {
        IPage<Inport> page=new Page<>(inportVo.getPage(),inportVo.getLimit());
        QueryWrapper<Inport> qw=new QueryWrapper<>();
        qw.eq(inportVo.getGoodsid()!=null,"goodsid",inportVo.getGoodsid());
        qw.eq(inportVo.getProviderid()!=null,"providerid",inportVo.getProviderid());
        qw.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getStartTime());
        qw.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());
        qw.orderByDesc("inporttime");
        this.inportMapper.selectPage(page,qw);
        List<Inport> records = page.getRecords();
        for (Inport record : records) {
            if(null!=record.getGoodsid()){
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!= record.getProviderid()){
                Provider provider = this.providerService.getById(record.getProviderid());
                record.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    @Override
    public Inport saveInport(Inport inport) {
        this.inportMapper.insert(inport);
        //更新库存
        Integer goodsId=inport.getGoodsid();
        Goods goods = this.goodsService.getById(goodsId);
        goods.setNumber(goods.getNumber()+inport.getNumber());
        this.goodsService.updateGoods(goods);
        return inport;
    }
    @CachePut(cacheNames = "com.lloyvet.business.service.impl.InportServiceImpl")
    @Override
    public Inport updateInport(Inport inport) {
        Inport oldObj=this.inportMapper.selectById(inport.getId());
        Goods goods = this.goodsService.getById(oldObj.getGoodsid());
        goods.setNumber(goods.getNumber()-oldObj.getNumber()+inport.getNumber());
        this.goodsService.updateGoods(goods);
        this.inportMapper.updateById(inport);
        return inport;
    }

    @Override
    public boolean removeById(Serializable id) {
        Inport inport = this.inportMapper.selectById(id);
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-inport.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }
}
