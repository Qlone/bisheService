package com.rabbit.service.impl;

import com.rabbit.bean.HqlBean;
import com.rabbit.dao.IBaseDao;
import com.rabbit.entity.OrdersEntity;
import com.rabbit.service.IListBean;
import com.rabbit.service.IOrderService;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
@Service
@Scope(value = "prototype")
public class OtherSerivce {

    private final IListBean<OrdersEntity> mOrdersEntityIListBean;
    private final IBaseDao<OrdersEntity> mOrdersEntityIBaseDao;
    @Autowired
    public OtherSerivce(IListBean<OrdersEntity> mOrdersEntityIListBean,IBaseDao<OrdersEntity> mOrdersEntityIBaseDao){
        this.mOrdersEntityIListBean = mOrdersEntityIListBean;
        this.mOrdersEntityIBaseDao = mOrdersEntityIBaseDao;
        mOrdersEntityIListBean.settName(OrdersEntity.class);
    }

    public String sendGoods(List<Integer> orderId){
       List<OrdersEntity> ordersEntities = new ArrayList<>();
       for(int id:orderId){
           OrdersEntity ordersEntity = mOrdersEntityIBaseDao.get(OrdersEntity.class,id);
           if(null == ordersEntity){
               return "fail";
           }else {
               ordersEntity.setStatus(IOrderService.ORDER_STATUS_ONWAY);
               ordersEntities.add(ordersEntity);
           }
       }
        try {
           mOrdersEntityIBaseDao.update(ordersEntities);
           RabbitLog.debug("更新成功："+ordersEntities.size());
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            RabbitLog.debug("更新失败");
            return "fail";
        }


    }

}
