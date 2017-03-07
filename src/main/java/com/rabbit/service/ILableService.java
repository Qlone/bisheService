/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service;

import com.rabbit.entity.LableEntity;

/**
 * Created by weina on 2017/3/3.
 */
public interface ILableService {

    String LABLE_MARK_NORMAL = "normal";
    String LABLE_MARK_TITLE = "title";

    IListBean<LableEntity> getLableList(String text,int page ,int lines);

    void addOrSaveLable(String text);

    void  addOrSaveLable(String text, String mark);

    IListBean<LableEntity> getHotLableList(int page, int lines);
}
