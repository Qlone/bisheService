/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service;

import com.rabbit.entity.AddressEntity;

/**
 * Created by weina on 2017/3/13.
 */
public interface IAddressService {
    AddressEntity getAddressEntity(int addressId);

    IListBean<AddressEntity> getAddressList(int userId);

    boolean addAddress(AddressEntity addressEntity);

    boolean deleteAddress(int userId, int addressId);
}
