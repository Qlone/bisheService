/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.controller;

import com.rabbit.entity.AddressEntity;
import com.rabbit.service.impl.AddressService;
import com.rabbit.util.JsonUtil;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weina on 2017/3/17.
 */


@Service
@RequestMapping(value = "address")


public class AddressController {
    @Autowired
    private AddressService mAddressService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public String getAddressList(
            @RequestParam(value = "userId",required = false) int usrId){
        RabbitLog.debug("获取 地址列表  :"+usrId);
        return JsonUtil.toJson(mAddressService.getAddressList(usrId).getList());
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addAddress(
            @RequestBody AddressEntity addressEntity){
        RabbitLog.debug("添加地址  :"+addressEntity.getAddress());
        boolean res = mAddressService.addAddress(addressEntity);
        return ""+res;
    }
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String deleteAddress(
            @RequestParam(value = "userId",required = false) int userId,
            @RequestParam(value = "addressId",required = false) int addressId){
        RabbitLog.debug("删除地址  :"+addressId);
        boolean res = mAddressService.deleteAddress(userId,addressId);
        return ""+res;
    }

}
