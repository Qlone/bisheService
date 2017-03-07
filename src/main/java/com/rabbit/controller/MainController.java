/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.controller;

import com.rabbit.entity.GoodsEntity;
import com.rabbit.service.IGoodService;
import com.rabbit.util.OwnerFileSaver;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by weina on 2017/3/7.
 */

@Controller
@RequestMapping(value = "home")
public class MainController {
    @Autowired
    private IGoodService mIGoodService;
    /**
     * 获取  控制页面
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String homePage(){
        return "addGoods.jsp";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String applyPost(HttpSession session,
                            Model model,
                          @RequestParam(value = "title",required = false) String title,
                          @RequestParam(value = "price",required = false) double price,
                          @RequestParam(value = "type",required = false) String type,
                          @RequestParam(value = "stock",required = false) int stock,
                          @RequestParam(value = "status",required = false) String status,
                          @RequestParam(value = "picture",required=false) MultipartFile themeImg,
                          @RequestParam(value = "pictureGroup",required=false) MultipartFile[] themeImages){

        //文件路径上下文
        String contextPath = session.getServletContext().getRealPath("/");
        //图片数量
        final int count = themeImages.length;
        //图片 路径存储
        StringBuffer stringBuffer = new StringBuffer();
        String themeImgString =null;
        try {
            themeImgString = OwnerFileSaver.saveImage(themeImg,contextPath);
            for (int i = 0; i < count; i++) {
                stringBuffer.append(OwnerFileSaver.saveImage(themeImages[i], contextPath)+" ");
                RabbitLog.debug("图片存储 : " + i);
            }
            //保存对象
            GoodsEntity goodsEntity = new GoodsEntity();
            goodsEntity.setGoodsDelete(IGoodService.GOODS_DELETE_FALSE);
            goodsEntity.setTitle(title);
            goodsEntity.setPicture(themeImgString);
            goodsEntity.setPrice(price);
            goodsEntity.setSales(0);
            goodsEntity.setStatus(status);
            goodsEntity.setType(type);
            goodsEntity.setStock(stock);
            goodsEntity.setViews(0);
            //图片保存
            goodsEntity.setPictureGroup(stringBuffer.toString());
            //添加商品
            mIGoodService.addGoods(goodsEntity);
            model.addAttribute("msg"," success " + goodsEntity.getTitle());
            return "res.jsp";

        } catch (Exception e) {
            //服务器存储错误
            RabbitLog.debug("图片存储 失败");
            model.addAttribute("msg"," failed ");
            return "res.jsp";
        }
    }
}
