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
import com.rabbit.entity.AddressEntity;
import com.rabbit.entity.OrdersEntity;
import com.rabbit.service.IAddressService;
import com.rabbit.service.IListBean;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;

/**
 * Created by weina on 2017/3/13.
 */
@Service
@Scope(value = "prototype")
public class AddressService implements IAddressService{
    private final IBaseDao<AddressEntity> mAddressEntityIBaseDao;
    private final IListBean<AddressEntity> mAddressEntityIListBean;

    @Autowired
    public AddressService(IBaseDao<AddressEntity> mAddressEntityIBaseDao, IListBean<AddressEntity> mAddressEntityIListBean) {
        this.mAddressEntityIBaseDao = mAddressEntityIBaseDao;
        this.mAddressEntityIListBean = mAddressEntityIListBean;
        mAddressEntityIListBean.settName(AddressEntity.class);
    }

    /**
     * 获取 单一 地址
     * @param addressId
     * @return
     */
    @Override
    public AddressEntity getAddressEntity(int addressId){
        return mAddressEntityIBaseDao.get(AddressEntity.class,addressId);
    }

    /**
     * 获取地址列表
     * @param userId
     * @return
     */
    @Override
    public IListBean<AddressEntity> getAddressList(int userId){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and userId = ? ");
        hqlBean.setRulesHql(" order by addressId desc ");
        hqlBean.addObject(userId);
        mAddressEntityIListBean.init(hqlBean,1,Integer.MAX_VALUE);
        return mAddressEntityIListBean;
    }

    /**
     * 添加新的 地址
     * @param addressEntity
     * @return
     */
    @Override
    public boolean addAddress(AddressEntity addressEntity){
        try {
            mAddressEntityIBaseDao.save(addressEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAddress(int userId,int addressId){
        AddressEntity addressEntity = getAddressEntity(addressId);
        if(null != addressEntity){
            if(userId == addressEntity.getUserId()){
                try {
                    mAddressEntityIBaseDao.delete(addressEntity);
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
