/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.controller;

import com.rabbit.entity.CommentEntity;
import com.rabbit.service.impl.CommentService;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by weina on 2017/3/28.
 */
@Service
@RequestMapping(value = "comment")
public class CommentController {
    private final CommentService mCommentService;

    @Autowired
    public CommentController(CommentService mCommentService) {
        this.mCommentService = mCommentService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addComment(
            @RequestBody CommentEntity commentEntity){
        RabbitLog.debug(" 新的评论 ");
        if(null!=commentEntity) {
            return "" + mCommentService.saveComment(commentEntity.getUserId(), commentEntity);
        }else {
            return "false";
        }
    }

}
