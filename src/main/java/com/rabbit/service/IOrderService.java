/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service;

/**
 * Created by weina on 2017/3/10.
 */
public interface IOrderService {
    /**
     * 删除 订单
     */
    String ORDER_STATUS_DEDLETE = "delete";

    /**
     * 订单 尚未付款 ，也就是 在购物车里面了吧
     */
    String ORDER_STATUS_NOPAY = "not_pay";
    /**
     * 订单 已经付款 商家没有发货
     */
    String ORDER_STATUS_PAID = "pay_no_send";

    /**
     * 订单 已经发货 正在路上
     */
    String ORDER_STATUS_ONWAY = "pay_on_way";
    /**
     * 订单 已经收到
     */
    String ORDER_STATUS_GET = "pay_and_get";

    /**
     * 订单 评论
     */
    String ORDER_STATUS_COMMENT="pay_and_commend";

    boolean addOrderToCart(int userId, int addressId, int goodId, int amount);
}
