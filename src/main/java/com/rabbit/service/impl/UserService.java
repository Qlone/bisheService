/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.service.impl;

import com.rabbit.bean.GsonLogin;
import com.rabbit.dao.IBaseDao;
import com.rabbit.dao.daoImpl.BaseDao;
import com.rabbit.entity.UserEntity;
import com.rabbit.service.IUserService;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2017/3/1.
 */
@Service
public class UserService implements IUserService {

    IBaseDao<UserEntity> mUserDao = new BaseDao<UserEntity>();
    @Override
    public GsonLogin login(UserEntity userEntity) {
        GsonLogin gsonLogin = new GsonLogin();
        gsonLogin.setUserEntity(isUserExists(userEntity));

        gsonLogin.setBoolean(null !=gsonLogin.getUserEntity());
        RabbitLog.debug("登录"+userEntity.getUserName()+" 密码 " +userEntity.getPassword() + "  " +gsonLogin.isBoolean());
        return gsonLogin;
    }
    @Override
    public void loginOut() {
        HttpSession session = CheckUser.getRequest().getSession(true);
        session.removeAttribute(IUserService.SESSION_USER);
    }

    //添加session
    private void addLoginSession(Object userEntity){
        HttpSession session = CheckUser.getRequest().getSession(true);
        session.setAttribute(IUserService.SESSION_USER,userEntity);
        session.setMaxInactiveInterval(IUserService.SESSION_TIME);
    }

    private UserEntity isUserExists(UserEntity userEntity){
        String hql = " from UserEntity where userName = ? and password = ? ";
        List<Object> param = new ArrayList();
        param.add(userEntity.getUserName());
        param.add(userEntity.getPassword());
        try {
            UserEntity res = mUserDao.get(hql, param);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            RabbitLog.debug("查看用户是否存在时,用户获取失败");
            return null;
        }
    }

}
