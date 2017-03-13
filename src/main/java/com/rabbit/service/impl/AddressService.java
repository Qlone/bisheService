/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.impl;

import com.rabbit.dao.IBaseDao;
import com.rabbit.entity.AddressEntity;
import com.rabbit.entity.OrdersEntity;
import com.rabbit.service.IAddressService;
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
    @Autowired
    IBaseDao<AddressEntity> mAddressEntityIBaseDao;

    @Override
    public AddressEntity getAddressEntity(int addressId){
        return mAddressEntityIBaseDao.get(AddressEntity.class,addressId);
    }

}
