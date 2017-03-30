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
import com.rabbit.util.JsonUtil;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取分数
     */
    @RequestMapping(value = "/getScore",method = RequestMethod.GET)
    @ResponseBody
    public String getScore(
            @RequestParam(value = "goodsId",required = false)int goodsId){
        double rsc = mCommentService.getCommentsScore(goodsId);
        RabbitLog.debug(" 获取分数 "+rsc);
        return ""+rsc;
    }

    @RequestMapping(value = "/commentCount",method = RequestMethod.GET)
    @ResponseBody
    public String getCommentCount(
            @RequestParam(value = "goodsId",required = false)int goodsId){
        long rsc  = mCommentService.getCommentsCount(goodsId);
        RabbitLog.debug(" 获取评论数量 "+rsc);
        return ""+rsc;
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public String getCommentList(
            @RequestParam(value = "goodsId",required = false)int goodsId,
            @RequestParam(value = "page",required = false)int page,
            @RequestParam(value = "lines",required = false)int lines){
        RabbitLog.debug(" 获取评论列表 ");
        return JsonUtil.toJson(mCommentService.getComments(goodsId, page, lines).getList());
    }
}
