package com.lloyvet.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lloyvet.business.domain.Goods;
import com.lloyvet.business.domain.Inport;
import com.lloyvet.business.domain.Provider;
import com.lloyvet.business.mapper.InportMapper;
import com.lloyvet.business.service.GoodsService;
import com.lloyvet.business.service.ProviderService;
import com.lloyvet.business.vo.OutportVo;
import com.lloyvet.system.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lloyvet.business.mapper.OutportMapper;
import com.lloyvet.business.domain.Outport;
import com.lloyvet.business.service.OutportService;
@Service
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService{

    @Autowired
    private OutportMapper outportMapper;
    @Autowired
    private InportMapper inportMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProviderService providerService;
    @Override
    @CachePut(cacheNames = "com.lloyvet.business.service.impl.OutportServiceImpl",key = "#result.id")
    public Outport saveOutport(Outport outport) {
        Integer inportid = outport.getInportid();
        Inport inport = inportMapper.selectById(inportid);
        outport.setGoodsid(inport.getGoodsid());
        outport.setNumber(outport.getNumber());
        outport.setPaytype(inport.getPaytype());
        outport.setOutportprice(inport.getInportprice());
        outport.setProviderid(inport.getProviderid());
        outportMapper.insert(outport);
        //减少库存
        Goods goods = goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-outport.getNumber());
        goodsService.updateGoods(goods);
        return outport;
    }

    @Override
    public DataGridView queryAllOutport(OutportVo outportVo) {
        IPage<Outport> page=new Page<>(outportVo.getPage(),outportVo.getLimit());
        QueryWrapper<Outport> qw=new QueryWrapper<>();
        qw.eq(outportVo.getGoodsid()!=null,"goodsid",outportVo.getGoodsid());
        qw.eq(outportVo.getProviderid()!=null,"providerid",outportVo.getProviderid());
        qw.ge(outportVo.getStartTime()!=null,"outporttime",outportVo.getStartTime());
        qw.le(outportVo.getEndTime()!=null,"outporttime",outportVo.getEndTime());
        qw.orderByDesc("outporttime");
        this.outportMapper.selectPage(page,qw);
        List<Outport> records = page.getRecords();
        for (Outport record : records) {
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
}
