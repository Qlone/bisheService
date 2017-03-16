/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.impl;


import com.rabbit.bean.HqlBean;
import com.rabbit.service.IListBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weina on 2016/12/4.
 */
@Component
@Scope(value = "prototype")
public abstract class AbstractListBean<T> implements IListBean<T> {
    private List<T> listBean;
    private long currentPage;
    private long maxPage;
    private long currentLines;
    private Class tName;
    private HqlBean object;
    @Override
    public Class gettName() {
        return tName;
    }

    @Override
    public void settName(Class tName) {
        this.tName = tName;
    }

    @Override
    public void init(int page, int lines) {
        this.init(null,page,lines);
    }

    @Override
    public void init(HqlBean object, int page, int lines) {
        long maxNumber;
        this.object =object;
        //获取数量
        maxNumber =count();
        listBean=null;
        currentLines =0;
        maxPage=0;
        currentPage=page;
        //测试是否超过
        if((page-1)*lines>maxNumber){//如果超了就退出
            return;
        }
        //计算最大页面
        if (maxNumber % lines == 0) {
            maxPage = maxNumber/lines;
        } else {
            maxPage = maxNumber/lines+1;
        }
        listBean = initList(page,lines);
        if(null != listBean) {
            currentLines = listBean.size();
        }else {
            currentLines =0;
        }


    }

    protected abstract List<T> initList(int page,int lines);
    protected abstract long count();

    @Override
    public List<T> getList() {
        return this.listBean;
    }

    @Override
    public long getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public long getMaxPages() {
        return this.maxPage;
    }

    @Override
    public long getNumer() {
        return this.currentLines;
    }

    public HqlBean getObject() {
        return this.object;
    }
}
