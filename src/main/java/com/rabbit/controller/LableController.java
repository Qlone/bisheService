/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.controller;

import com.rabbit.entity.LableEntity;
import com.rabbit.service.ILableService;
import com.rabbit.service.impl.LableService;
import com.rabbit.util.JsonUtil;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weina on 2017/3/3.
 */
@Controller
@RequestMapping(value = "lable")
public class LableController {
    @Autowired
    private ILableService mLableService;

    @RequestMapping(value = "/hint",method = RequestMethod.GET)
    @ResponseBody
    public String getHintLable(
            @RequestParam(value = "text", required=false) String text,
            @RequestParam(value = "page", required=false) int page,
            @RequestParam(value = "lines", required=false) int lines){

        RabbitLog.debug("获取标签推荐 text :  " + text);
        return JsonUtil.toJson(mLableService.getLableList(text,page,lines).getList());
    }

    /**
     * 添加新的标签或者更新标签
     * @param text
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void addLable(
            @RequestBody final LableEntity text){

        RabbitLog.debug("确认搜索 text :  " + text.getText());
        new Runnable() {
            @Override
            public void run() {
                mLableService.addOrSaveLable(text.getText());
            }
        }.run();
        return ;
    }

    @RequestMapping(value = "/hot",method = RequestMethod.GET)
    @ResponseBody
    public String getHotLable(
            @RequestParam(value = "page", required=false) int page,
            @RequestParam(value = "lines", required=false) int lines){
        RabbitLog.debug(" 推送  火热 搜索 ");
        return JsonUtil.toJson(mLableService.getHotLableList(page,lines).getList());
    }

}
