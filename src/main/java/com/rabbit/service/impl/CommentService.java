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
import com.rabbit.entity.CommentEntity;
import com.rabbit.entity.UserEntity;
import com.rabbit.service.ICommentService;
import com.rabbit.service.list.BaseList;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by weina on 2017/3/28.
 */
@Service
@Scope(value = "prototype")
public class CommentService implements ICommentService {
    private final IBaseDao<CommentEntity> mCommentEntityIBaseDao;
    private final OrderService mOrderService;
    private final BaseList<CommentEntity> mCommentEntityBaseList;
    private final UserService mUserService;

    @Autowired
    public CommentService(IBaseDao<CommentEntity> mCommentEntityIBaseDao, OrderService mOrderService, BaseList<CommentEntity> mCommentEntityBaseList, UserService mUserService) {
        this.mCommentEntityIBaseDao = mCommentEntityIBaseDao;
        this.mOrderService = mOrderService;
        this.mCommentEntityBaseList = mCommentEntityBaseList;
        mCommentEntityBaseList.settName(CommentEntity.class);
        this.mUserService = mUserService;
    }

    /**
     * 保存 新的评论
     */
    @Override
    public boolean saveComment(int userId, CommentEntity commentEntity){
        if(null != commentEntity && userId == commentEntity.getUserId()){
            try {
                if(mOrderService.OrderComment(userId,commentEntity.getOrderId())) {
                    UserEntity userEntity = mUserService.getUserById(userId);
                    commentEntity.setUserName(userEntity.getUserName());
                    commentEntity.setCommentData(new Date());
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
    /**
     * 获取 评论
     */
    @Override
    public BaseList<CommentEntity> getComments(int goodsId, int page, int lines){

        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and goodsId = ? ");
        hqlBean.setRulesHql(" order by commentId desc ");
        hqlBean.addObject(goodsId);
        mCommentEntityBaseList.init(hqlBean,page,lines);
        return mCommentEntityBaseList;
    }
    @Override
    public long getCommentsCount(int goodsId){
        String hql = "select count(*) from  CommentEntity where goodsId = ?";
        Object[] param = {goodsId};
        return mCommentEntityIBaseDao.count(hql,param);
    }
    @Override
    public double getCommentsScore(int goodsId){
        try {

            String hql = " select avg(start) from CommentEntity where goodsId = ?";
            Object[] param = {goodsId};
            return mCommentEntityIBaseDao.countAvg(hql, param);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
