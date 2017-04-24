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
        gsonLogin.setUserEntity(isUserExists(userEntity,true));

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

    private UserEntity isUserExists(UserEntity userEntity,Boolean isCheckPassword){
        StringBuffer hql = new StringBuffer();
        hql.append(" from UserEntity where userName = ?");
        List<Object> param = new ArrayList();
        param.add(userEntity.getUserName());
        if(isCheckPassword){
            hql.append("  and password = ?");
            param.add(userEntity.getPassword());
        }
        try {
            UserEntity res = mUserDao.get(hql.toString(), param);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            RabbitLog.debug("查看用户是否存在时,用户获取失败");
            return null;
        }
    }
    @Override
    public UserEntity getUserById(int userId){
        return mUserDao.get(UserEntity.class,userId);
    }
    @Override
    public void updataUser(UserEntity userEntity) throws Exception {
        mUserDao.update(userEntity);
    }

    @Override
    public UserEntity changeUserPayPassword(int userId, int oldPsw, int newPsw){
        UserEntity userEntity = getUserById(userId);
        if(null != userEntity && userEntity.getPayPassword() == oldPsw){
            userEntity.setPayPassword(newPsw);
            try {
                mUserDao.update(userEntity);
                return userEntity;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }
    @Override
    public UserEntity changeUserPassword(int userId,String oldPsw,String newPsw){
        UserEntity userEntity = getUserById(userId);
        if(null != userEntity && userEntity.getPassword().equals(oldPsw)){
            userEntity.setPassword(newPsw);
            try {
                mUserDao.update(userEntity);
                return userEntity;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    public String register(UserEntity userEntity){
        if(null == userEntity|| null == userEntity.getUserName() || null == userEntity.getPassword() ){
            return "errorParam";
        }

        if(null == isUserExists(userEntity,false)){
            try {
                mUserDao.save(userEntity);
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return "fail";
            }
        }else {
            return "exits";
        }
    }


}
