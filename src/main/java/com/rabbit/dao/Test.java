/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.dao;

import com.rabbit.dao.daoImpl.BaseDao;
import com.rabbit.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by weina on 2016/11/25.
 */
@Repository
public class Test {

    public static void main(String[] args){

        UserEntity userEntity  = new UserEntity();
        userEntity.setPassword("asd");
        userEntity.setUserName("first people");
        userEntity.setUserType("normal");
        userEntity.setUserStatus("normal");
        IBaseDao<UserEntity> baseDao = new BaseDao<UserEntity>();
        try {
            baseDao.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("success");


    }
}
