/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.list;

import com.rabbit.dao.IBaseDao;
import com.rabbit.entity.GoodsEntity;
import com.rabbit.service.impl.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weina on 2017/3/2.
 */
@Component
public class GoodsList extends AbstractListBean<GoodsEntity> {
    final
    IBaseDao<GoodsEntity> mGoodsDao;

    @Autowired
    public GoodsList(IBaseDao<GoodsEntity> mGoodsDao) {
        this.mGoodsDao = mGoodsDao;
    }

    public static void main(String[] args){

    }

    @Override
    protected List<GoodsEntity> initList(int page, int lines) {
        String hql = "from GoodsEntity where 1=1 " + getObject().getInnerHql() + getObject().getRulesHql();

        return mGoodsDao.find(hql,getObject().getInnerParam(),page,lines);
    }

    @Override
    protected long count() {
        String hql = "select count(*) from GoodsEntity where 1=1 "+ getObject().getInnerHql();
        return mGoodsDao.count(hql,getObject().getInnerParam());
    }
}
