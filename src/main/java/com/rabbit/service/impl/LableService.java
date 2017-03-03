/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.impl;

import com.rabbit.bean.HqlBean;
import com.rabbit.dao.daoImpl.BaseDao;
import com.rabbit.entity.LableEntity;
import com.rabbit.service.ILableService;
import com.rabbit.service.IListBean;
import com.rabbit.service.list.BaseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weina on 2017/3/3.
 */
@Service
public class LableService implements ILableService{
    private final IListBean<LableEntity> mLableEntityIListBean;

    @Autowired
    public LableService(BaseList<LableEntity> mLableEntityIListBean) {
        this.mLableEntityIListBean = mLableEntityIListBean;
        mLableEntityIListBean.settName(LableEntity.class);
    }

    @Override
    public IListBean<LableEntity> getLableList(String text,int page,int lines) {
        return getLableList(text.split("\\s+"),page,lines);
    }

    private IListBean<LableEntity> getLableList(String[] strings,int page,int lines){
        HqlBean hqlBean = new HqlBean();
        StringBuffer stringBuffer = new StringBuffer();
        for(String s:strings){
            stringBuffer.append(" and text like ? ");
            hqlBean.addObject('%'+s+'%');
        }
        hqlBean.setInnerHql(stringBuffer.toString());
        hqlBean.setRulesHql(" order by view desc ");
        mLableEntityIListBean.init(hqlBean,page,lines);
        return mLableEntityIListBean;
    }
}
