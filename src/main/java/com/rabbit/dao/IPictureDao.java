/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.dao;

import com.rabbit.entity.PhotoEntity;

import java.util.List;

/**
 * Created by weina on 2016/12/10.
 */
public interface IPictureDao {
    //用于图片 的dao 层

    /**
     * 保存图片
     * @param list  图片的地址
     * @param photoGroup 图片组的id 需要保证唯一性 最好是代号+ 时间戳
     * @return 成功 true ，失败false
     */
    boolean savePicture(List<String> list, String photoGroup);

    /**
     *  获取图片
     * @param photoGroup 图片组的id
     * @return 成功返回列表失败 null
     */
    List<PhotoEntity> getPictures(String photoGroup);
}
