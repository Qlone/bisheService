/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service;

import com.rabbit.bean.GsonLogin;
import com.rabbit.entity.UserEntity;

/**
 * Created by weina on 2017/3/1.
 */
public interface IUserService {
    String SESSION_USER = "user";
    int SESSION_TIME = 1*60*60;
    /**
     * 功能：登录成功后，注册一个session, 名字为 user
     * @param userEntity 用户输入的信息
     * @return 返回一个gson 登录成功 里面boalean值 为true，失败则相反
     */
    GsonLogin login(UserEntity userEntity);

    /**
     * 功能：登出
     */
    void loginOut();
}
