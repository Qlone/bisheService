package com.rabbit.service.impl;

import com.rabbit.bean.HqlBean;
import com.rabbit.dao.IBaseDao;
import com.rabbit.dao.daoImpl.BaseDao;
import com.rabbit.entity.GoodsEntity;
import com.rabbit.service.IGoodService;
import com.rabbit.service.IListBean;
import com.rabbit.service.list.GoodsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2017/3/3.
 */
@Service
@Scope(value = "prototype")
public class GoodService implements IGoodService {
    final private IListBean<GoodsEntity> goodsListBean;

    @Autowired
    public GoodService(GoodsList goodsListBean) {
        this.goodsListBean = goodsListBean;
    }
    public static void main(String[] args){
        GoodService goodService = new GoodService(new GoodsList(new BaseDao<GoodsEntity>()));
        IListBean<GoodsEntity> goodsListBean =  goodService.getAllList(1,2);
        System.out.println(goodsListBean.getList().size());
    }

    @Override
    public IListBean<GoodsEntity> getAllList(int page, int lines) {
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and goodsDelete = ? ");
        hqlBean.setRulesHql(" order by goodsId desc ");
        hqlBean.addObject(GOODS_DELETE_FALSE);
        goodsListBean.init(hqlBean,page,lines);
        return goodsListBean;
    }

    @Override
    public IListBean<GoodsEntity> getHotList(int page, int lines) {
        return getStatusList(GOODS_STATUS_HOT,page,lines);
    }

    @Override
    public IListBean<GoodsEntity> getRecommandList(int page, int lines) {
        return getStatusList(GOODS_STATUS_RECOMMEND,page,lines);
    }

    @Override
    public IListBean<GoodsEntity> getTypeList(String types, int page, int lines) {
        return null;
    }

    @Override
    public IListBean<GoodsEntity> getTitleList(String title, int page, int lines) {
        return null;
    }

    @Override
    public IListBean<GoodsEntity> getGoodsItem(int goodsId) {
        return null;
    }

    @Override
    public IListBean<GoodsEntity> getStatusList(String type, int page, int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and status = ? and goodsDelete = ?");
        hqlBean.setRulesHql(" order by goodsId desc ");
        hqlBean.addObject(type);
        hqlBean.addObject(GOODS_DELETE_FALSE);
        goodsListBean.init(hqlBean,page,lines);
        return goodsListBean;
    }
}
