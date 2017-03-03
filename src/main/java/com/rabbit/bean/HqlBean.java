/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2016/12/19.
 */
public class HqlBean {
    private String innerHql;
    private String rulesHql;
    private List<Object> innerParam;
    public HqlBean(){
        innerHql ="";
        rulesHql ="";
        innerParam = new ArrayList<>();
    }

    public String getInnerHql() {
        return innerHql;
    }

    public void setInnerHql(String innerHql) {
        this.innerHql = innerHql;
    }

    public List<Object> getInnerParam() {
        return innerParam;
    }

    public void addObject(Object object){
        innerParam.add(object);
    }

    public void setRulesHql(String rulesHql) {
        this.rulesHql = rulesHql;
    }

    public String getRulesHql() {
        return rulesHql;
    }

    public void setInnerParam(List<Object> innerParam) {
        this.innerParam = innerParam;
    }
}
