/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.list;

import com.rabbit.bean.HqlBean;
import com.rabbit.dao.IBaseDao;
import com.rabbit.dao.daoImpl.BaseDao;
import com.rabbit.entity.LableEntity;
import com.rabbit.service.IListBean;
import com.rabbit.service.impl.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by weina on 2017/3/3.
 */
@Component
@Scope(value = "prototype")
public class BaseList<T> extends AbstractListBean<T> {
    private final IBaseDao<T> iBaseDao;
    @Autowired
    public BaseList(IBaseDao<T> iBaseDao) {
        this.iBaseDao = iBaseDao;

    }
    public static void main(String[] a){
        IListBean<LableEntity> lableEntityIListBean = new BaseList<LableEntity>(new BaseDao<LableEntity>());
        HqlBean hqlBean =  new HqlBean();
        lableEntityIListBean.init(hqlBean,1,2);
        System.out.println(lableEntityIListBean.getList().size());
    }

    @Override
    protected List<T> initList(int page, int lines) {
        String hql = "from "+ gettName().getName()+" where 1=1 " + getObject().getInnerHql() + getObject().getRulesHql();

        return iBaseDao.find(hql,getObject().getInnerParam(),page,lines);
    }

    @Override
    protected long count() {
        String hql = "select count(*) from "+gettName().getName()+" where 1=1 "+ getObject().getInnerHql();
        return iBaseDao.count(hql,getObject().getInnerParam());
    }
}
