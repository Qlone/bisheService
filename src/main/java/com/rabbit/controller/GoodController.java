/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.controller;

import com.rabbit.service.impl.GoodService;
import com.rabbit.util.JsonUtil;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weina on 2017/3/2.
 */
@Controller
@RequestMapping(value = "good")
public class GoodController {
    @Autowired
    GoodService mGoodService;
    /**
     * 获取所有列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public String  listAll(
                           @RequestParam(value = "page", required=false) int page,
                           @RequestParam(value = "lines",required = false) int lines){
        RabbitLog.debug("获取所有商品列表 page " + page +" lines" + lines);
        return JsonUtil.toJson(mGoodService.getAllList(page,lines).getList());
    }


    @RequestMapping(value = "/list/{status}",method = RequestMethod.GET)
    @ResponseBody
    public String  listStatus(@PathVariable String status,
                           @RequestParam(value = "page", required=false) int page,
                           @RequestParam(value = "lines",required = false) int lines){
        RabbitLog.debug("获取所有特定商品" + status + "列表 page " + page +" lines" + lines);
        return JsonUtil.toJson(mGoodService.getStatusList(status,page,lines).getList());
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public String listSearchByTitle(
            @RequestParam(value = "title", required=false) String title,
            @RequestParam(value = "page", required=false) int page,
            @RequestParam(value = "lines",required = false) int lines){
        RabbitLog.debug(" 搜索商品  " + title +"列表 page " + page +" lines" + lines);
        return JsonUtil.toJson(mGoodService.getTitleList(title,page,lines).getList());
    }
    @RequestMapping(value = "/getItem",method = RequestMethod.GET)
    @ResponseBody
    public String getGoodDetails(
            @RequestParam(value = "goodsId", required=false) int goodsId){
         RabbitLog.debug("获取指定商品  :  "+goodsId);
         return JsonUtil.toJson(mGoodService.getGoodsItem(goodsId).getList());
    }
}
