/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weina on 2016/11/25.
 */
public interface IBaseDao<T> {

    Serializable save(T o) throws Exception;
    Serializable save(List<T> o)throws  Exception;
    void delete(T o);//此功能尚未完成
    boolean insert(String sql, Object[] params);

    boolean  delete(String sql, Integer id);
    boolean update(String sql, Object[] params);
    void update(T o) throws Exception;
    Serializable update(List<T> o) throws Exception;
    void saveOrUpdate(T o);
    List<Object> executeSQL(String sql, Object[] param);
    List<T> find(String hql);
    int countNum(String hql, Object[] param);
    List<T> find(String hql, Object[] param);

    List<T> find(String hql, List<Object> param);

    List<T> find(String hql, Object[] param, Integer page, Integer rows);

    List<T> findNumberRows(String hql, Object[] param, Integer first, Integer max);

    List<T> findNumberRows(String hql, List param, Integer first, Integer max);

    List<T> find(String hql, List<Object> param, Integer page, Integer rows);

    T get(Class<T> c, Serializable id);

    T get(String hql, Object[] param);

    T get(String hql, List<Object> param);

    Long count(String hql);

    Long count(String hql, Object[] param);

    Long count(String hql, List<Object> param);

    Integer executeHql(String hql);//此功能尚未完成

    Integer executeHql(String hql, Object[] param);//此功能尚未完成

    Integer executeHql(String hql, List<Object> param);//此功能尚未完成
}
