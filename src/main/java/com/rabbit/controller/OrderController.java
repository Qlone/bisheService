/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.controller;

import com.rabbit.bean.GsonAddOrder;
import com.rabbit.service.IOrderService;
import com.rabbit.service.impl.OrderService;
import com.rabbit.util.JsonUtil;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weina on 2017/3/10.
 */


@Service
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    private IOrderService mOrderService;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public String newOrder(
            @RequestParam(value = "userId",required = false) int userId,
            @RequestParam(value = "goodsId",required = false) int goodsId,
            @RequestParam(value = "addressId",required = false) int addressId,
            @RequestParam(value = "amount",required = false) int amount){
        RabbitLog.debug("兔子登记 接收新的订单 ");
        try {
            RabbitLog.debug("为了测试效果，等待 3秒钟 ");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RabbitLog.debug("是否存在 :  "+mOrderService.checkOrderIsExists(userId,goodsId));
        if(mOrderService.checkOrderIsExists(userId,goodsId)){
            return "exists";
        }else {

            if (mOrderService.addOrderToCart(userId, addressId, goodsId, amount)) {
                return "success";
            } else {
                return "error";
            }
        }
    }
    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    @ResponseBody
    public String getOrderToCart(
            @RequestParam(value = "userId",required = false) int userId,
            @RequestParam(value = "status",required = false) String status,
            @RequestParam(value = "page",required = false) int page,
            @RequestParam(value = "lines",required = false) int lines){
        RabbitLog.debug("请求 订单列表"+status+"   userId :"+userId);
        switch (status){
            case IOrderService.ORDER_STATUS_CART: {
                return JsonUtil.toJson(mOrderService.getOrderToCart(userId, page, lines).getList());
            }
            default:break;
        }
        return null;
    }
    //修改购物车中的数量
    @RequestMapping(value = "/changeAmount" ,method = RequestMethod.GET)
    @ResponseBody
    public String changeOrderCartAmount(
            @RequestParam(value = "userId",required = false) int userId,
            @RequestParam(value = "orderId",required = false) int orderId,
            @RequestParam(value = "amount",required = false) int amount){
        RabbitLog.debug("修改订单 请求 usrId:" + userId );
        boolean rs = mOrderService.updataOrderCartAmount(userId,orderId,amount);
        RabbitLog.debug("修改订单 请求 usrId:" + userId +" res: "+rs);
        if(rs){
            return ""+amount;
        }else {
            return ""+ -1;
        }
    }
    //修改购物车中的数量
    @RequestMapping(value = "/deleteCart" ,method = RequestMethod.GET)
    @ResponseBody
    public String deleteCart(
            @RequestParam(value = "userId",required = false) int userId,
            @RequestParam(value = "orderId",required = false) int orderId){
        RabbitLog.debug("删除订单 请求 usrId:" + userId);
        return  ""+mOrderService.deleteCart(userId,orderId);
    }

    //添加订单
    @RequestMapping(value = "/notPayOrder" ,method = RequestMethod.POST)
    @ResponseBody
    public String addOrderNotPay(
            @RequestBody GsonAddOrder gsonAddOrder){
        RabbitLog.debug("订单请求 "+gsonAddOrder.getOrderIdList().size()+"条");
        try {
            RabbitLog.debug("为了测试效果，等待 3秒钟 ");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return JsonUtil.toJson(mOrderService.addOrderButNotPay(gsonAddOrder));
    }
}
