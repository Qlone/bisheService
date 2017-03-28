/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.impl;

import com.rabbit.dao.IBaseDao;
import com.rabbit.entity.CommentEntity;
import com.rabbit.service.ICommentService;
import com.rabbit.service.list.BaseList;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by weina on 2017/3/28.
 */
@Service
@Scope(value = "prototype")
public class CommentService implements ICommentService {
    private final IBaseDao<CommentEntity> mCommentEntityIBaseDao;
    private final OrderService mOrderService;
    private final BaseList<CommentEntity> mCommentEntityBaseList;

    @Autowired
    public CommentService(IBaseDao<CommentEntity> mCommentEntityIBaseDao, OrderService mOrderService, BaseList<CommentEntity> mCommentEntityBaseList) {
        this.mCommentEntityIBaseDao = mCommentEntityIBaseDao;
        this.mOrderService = mOrderService;
        this.mCommentEntityBaseList = mCommentEntityBaseList;
    }

    /**
     * 保存 新的评论
     */
    @Override
    public boolean saveComment(int userId, CommentEntity commentEntity){
        if(null != commentEntity && userId == commentEntity.getUserId()){
            try {
                if(mOrderService.OrderComment(userId,commentEntity.getOrderId())) {
                    mCommentEntityIBaseDao.save(commentEntity);
                    RabbitLog.debug("保存了新的评论: "+userId+"   context" +
                            commentEntity.getContext());
                    return true;
                }else{
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RabbitLog.debug("评论 保存失败:  "+userId);
        return false;
    }

}
