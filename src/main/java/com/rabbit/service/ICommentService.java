/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service;

import com.rabbit.entity.CommentEntity;
import com.rabbit.service.list.BaseList;

/**
 * Created by weina on 2017/3/28.
 */
public interface ICommentService {
    boolean saveComment(int userId, CommentEntity commentEntity);

    BaseList<CommentEntity> getComments(int goodsId, int page, int lines);

    long getCommentsCount(int goodsId);

    double getCommentsScore(int goodsId);
    /**
     * 评论 服务
     */
}
