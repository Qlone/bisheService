/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.controller;

import com.rabbit.bean.GsonLogin;
import com.rabbit.service.IUserService;
import com.rabbit.util.JsonUtil;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by weina on 2017/3/1.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    IUserService userService;
    /**
     * 登录
     * @param gsonLogin
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody GsonLogin gsonLogin){
        GsonLogin res = userService.login(gsonLogin.getUserEntity());
        RabbitLog.debug(gsonLogin.getUserEntity().getUserName());
        return JsonUtil.toJson(res);
    }
}
