/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service;

import com.rabbit.bean.HqlBean;

import java.util.List;

/**
 * Created by weina on 2016/12/2.
 */
public interface IListBean<T> {

    Class gettName();

    void settName(Class tName);

    /**
     *
     * @param page 第几页
     * @param lines  多少行
     */
    void init(int page, int lines);
    void init(HqlBean object, int page, int lines);
    /**
     * 获取   商店 详细内容列表
     * @return 返回内容
     */
    List<T> getList();

    /**
     * 获取 当前页面
     * @return
     */
    long getCurrentPage();
    /**
     * 一共有多少页面
     */
    long getMaxPages();
    /**
     * 获取 数量
     */
    long getNumer();
}
