/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service;

import com.rabbit.entity.GoodsEntity;
import com.rabbit.service.list.GoodsList;

import java.util.List;

/**
 * Created by weina on 2017/3/2.
 */
public interface IGoodService {

    String GOODS_STATUS_NORMAL ="normal";
    String GOODS_STATUS_HOT = "hot";
    String GOODS_STATUS_RECOMMEND = "recommend";
    int GOODS_DELETE_TRUE = 0;
    int GOODS_DELETE_FALSE = 1;


    /**
     * 获取所有商品
     * @return
     */
    IListBean<GoodsEntity> getAllList(int page,int lines);

    /**
     * 获取热销商品
     * @return
     */
    IListBean<GoodsEntity> getHotList(int page,int lines);

    /**
     * 获取 推荐商品
     * @param page
     * @param lines
     * @return
     */
    IListBean<GoodsEntity> getRecommandList(int page,int lines);

    /**
     * 通过 类型 也就是标签来获取商品列表
     * @param types
     * @param page
     * @param lines
     * @return
     */
    IListBean<GoodsEntity> getTypeList(String types, int page, int lines);

    /**
     * 根据商品的标题来获取商品列表,可以根据空格隔开
     * @param title
     * @param page
     * @param lines
     * @return
     */
    IListBean<GoodsEntity> getTitleList(String title,int page,int lines);

    /**
     * 获取某个商品
     * @param goodsId
     * @return
     */
    IListBean<GoodsEntity> getGoodsItem(int goodsId);

    IListBean<GoodsEntity> getStatusList(String type, int page, int lines);

    boolean addGoods(GoodsEntity goodsEntity);

    void updataGoods(GoodsEntity goodsEntity) throws Exception;

    void updataGoods(List<GoodsEntity> goodsEntities) throws Exception;
}
