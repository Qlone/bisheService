/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.dao.daoImpl;

import com.rabbit.dao.IBaseDao;
import com.rabbit.dao.IPictureDao;
import com.rabbit.entity.PhotoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2016/12/10.
 */
@Repository
public class PitureDao implements IPictureDao {
    final
    IBaseDao<PhotoEntity> mDaoPhoto;

    @Autowired
    public PitureDao(IBaseDao<PhotoEntity> mDaoPhoto) {
        this.mDaoPhoto = mDaoPhoto;
    }

    @Override
    public boolean savePicture(List<String> list, String photoGroup) {
        try{
//            //这里使用new 防止出问题
//            List<PhotoEntity> photoList = new ArrayList<>();
//            for(String path:list){//添加数据
//                PhotoEntity photoEntity = new PhotoEntity();
//                photoEntity.setPhotoGroup(photoGroup);
//                photoEntity.setAddress(path);
//                photoList.add(photoEntity);
//            }
//            mDaoPhoto.save(photoList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PhotoEntity> getPictures(String photoGroup) {
        try {
            String hql = "from PhotoEntity where photoGroup = ? ";
            Object[] param = {photoGroup};
            return mDaoPhoto.find(hql,param);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
