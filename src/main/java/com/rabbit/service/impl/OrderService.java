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
import com.rabbit.entity.GoodsEntity;
import com.rabbit.entity.OrdersEntity;
import com.rabbit.service.IAddressService;
import com.rabbit.service.IGoodService;
import com.rabbit.service.IListBean;
import com.rabbit.service.IOrderService;
import com.rabbit.util.RabbitLog;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by weina on 2017/3/13.
 */
@Service
@Scope(value = "prototype")
public class OrderService implements IOrderService {
    private final IAddressService mIAddressService;
    private final IGoodService mIGoodService;
    private final IBaseDao<OrdersEntity> mOrdersEntityIBaseDao;
    private final IListBean<OrdersEntity> mOrdersEntityIListBean;

    @Autowired
    public OrderService(IAddressService mIAddressService, IGoodService mIGoodService, IBaseDao<OrdersEntity> mOrdersEntityIBaseDao, IListBean<OrdersEntity> mOrdersEntityIListBean) {
        this.mIAddressService = mIAddressService;
        this.mIGoodService = mIGoodService;
        this.mOrdersEntityIBaseDao = mOrdersEntityIBaseDao;
        this.mOrdersEntityIListBean = mOrdersEntityIListBean;
        mOrdersEntityIListBean.settName(OrdersEntity.class);
    }

    @Override
    public boolean addOrderToCart(int userId,int addressId,int goodId,int amount){
        AddressEntity addressEntity=null;
        GoodsEntity goodsEntity=null;
        OrdersEntity ordersEntity =null;

        try {
            addressEntity = mIAddressService.getAddressEntity(addressId);
            goodsEntity = mIGoodService.getGoodsItem(goodId).getList().get(0);
            ordersEntity = new OrdersEntity();
            ordersEntity.setUserId(userId);//用户id
            ordersEntity.setGoodsId(goodsEntity.getGoodsId());//商品id
            if(null != addressEntity) {
                ordersEntity.setAddress(addressEntity.getAddress());//设置地址
                ordersEntity.setPhone(addressEntity.getPhone());//设置收货地址
            }
            ordersEntity.setPicture(goodsEntity.getPicture());//设置图片
            ordersEntity.setTitle(goodsEntity.getTitle());//设置商品标题
            ordersEntity.setAmount(amount);//设置购买的数量
            ordersEntity.setPrice(goodsEntity.getPrice());//获取商品价格
            ordersEntity.setStatus(IOrderService.ORDER_STATUS_CART);//加入到购物车
            ordersEntity.setCreateTime(new Date());
            mOrdersEntityIBaseDao.save(ordersEntity);
            RabbitLog.debug("创建订单 成功  用户Id: "+userId+"   购买物品 ;"+goodsEntity.getTitle()+
                    "  数量 ："+amount);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            RabbitLog.debug("创建 订单失败  adrress is null："+(null == addressEntity)+
                    " ,goodsEntity is null："+(null == goodsEntity) );
            return false;
        }


    }

    //修改购物车数量
    @Override
    public boolean updataOrderCartAmount(int userId,int orderId,int amount){
        OrdersEntity ordersEntity = mOrdersEntityIBaseDao.get(OrdersEntity.class,orderId);
        GoodsEntity goodsEntity = mIGoodService.getGoodsItem(ordersEntity.getGoodsId()).getList().get(0);
        if(amount>goodsEntity.getStock() || amount < 1){
            RabbitLog.debug("修改购物车数量失败  ：不合法的数量");
            return false;
        }

        if(null !=ordersEntity && ordersEntity.getUserId()==userId){
            ordersEntity.setAmount(amount);
            try {
                mOrdersEntityIBaseDao.update(ordersEntity);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                RabbitLog.debug("更新购物车失败");
                return false;
            }
        }else {
            RabbitLog.debug("非法修改购物车 orderId:" + orderId +" user :" +userId);
            return false;
        }
    }

    @Override
    public boolean deleteCart(int userId, int orderId){
        OrdersEntity ordersEntity = mOrdersEntityIBaseDao.get(OrdersEntity.class,orderId);
        if(null!=ordersEntity&&userId == ordersEntity.getUserId() &&  ordersEntity.getStatus().equals(IOrderService.ORDER_STATUS_CART)){
            try {
                mOrdersEntityIBaseDao.delete(ordersEntity);
                RabbitLog.debug("删除订单 成功 userId :" +userId+"  orderId :"+orderId);
                return true;
            }catch (Exception e){
                RabbitLog.debug("删除订单 失败 userId :" +userId+"  orderId :"+orderId);
                e.printStackTrace();
            }
        }else{
            RabbitLog.debug("订单数据错误 userId :" +userId+"  orderId :"+orderId+"  order is null:" + (null == ordersEntity));
        }
        return false;
    }

    @Override
    public IListBean<OrdersEntity> getOrderToCart(int userId, int page, int lines){
        return getOrderByStatus(userId,IOrderService.ORDER_STATUS_CART,page,lines);
    }

    private IListBean<OrdersEntity> getOrderByStatus(int userId,String status,int page,int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql("  and userId = ? and status = ? ");
        hqlBean.setRulesHql(" order by createTime desc ");
        hqlBean.addObject(userId);
        hqlBean.addObject(status);
        //TODO:  完成  商品信息及时改动
        mOrdersEntityIListBean.init(hqlBean,page,lines);
        return mOrdersEntityIListBean;
    }
}
