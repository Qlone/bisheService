/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.impl;

import com.rabbit.bean.HqlBean;
import com.rabbit.dao.IBaseDao;
import com.rabbit.dao.daoImpl.BaseDao;
import com.rabbit.entity.LableEntity;
import com.rabbit.service.ILableService;
import com.rabbit.service.IListBean;
import com.rabbit.service.list.BaseList;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

/**
 * Created by weina on 2017/3/3.
 */
@Service
@Scope(value = "prototype")
public class LableService implements ILableService{
    private final IListBean<LableEntity> mLableEntityIListBean;
    private final IBaseDao<LableEntity> mLableEntityIBaseDao;

    @Autowired
    public LableService(BaseList<LableEntity> mLableEntityIListBean, IBaseDao<LableEntity> mLableEntityIBaseDao) {
        this.mLableEntityIListBean = mLableEntityIListBean;
        mLableEntityIListBean.settName(LableEntity.class);
        this.mLableEntityIBaseDao = mLableEntityIBaseDao;
    }

    @Override
    public IListBean<LableEntity> getLableList(String text,int page,int lines) {
        return getLableList(text.split("\\s+"),page,lines);
    }

    @Override
    public synchronized void  addOrSaveLable(String text){
         LableEntity lableEntity = getItem(text);
         if(null == lableEntity){
             lableEntity = new LableEntity();
             lableEntity.setMark(LABLE_MARK_NORMAL);
             lableEntity.setView(0);
         }
         lableEntity.setText(text);
         lableEntity.setView(lableEntity.getView()+1);
         try {
             mLableEntityIBaseDao.saveOrUpdate(lableEntity);
             RabbitLog.debug("保存更新 lable ："+text +"  成功");
         }catch (Exception e){
             e.printStackTrace();
             RabbitLog.debug("保存更新 lable ："+text +"  失败");
         }
    }

    @Override
    public IListBean<LableEntity> getHotLableList(int page,int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and mark = ? ");
        hqlBean.addObject(LABLE_MARK_NORMAL);
        hqlBean.setRulesHql(" order by view desc");
        mLableEntityIListBean.init(hqlBean,page,lines);
        return mLableEntityIListBean;
    }

    /**
     * 获取 lable 自动补全
     * @param strings
     * @param page
     * @param lines
     * @return
     */
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
    private LableEntity getItem(String text){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and text = ? ");
        hqlBean.addObject(text);
        try {
            mLableEntityIListBean.init(hqlBean, 1, 1);
            if(null != mLableEntityIListBean.getList()&&mLableEntityIListBean.getList().size()>0) {
                return mLableEntityIListBean.getList().get(0);
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            RabbitLog.debug("获取 lable失败 ");
            return null;
        }
    }
}
