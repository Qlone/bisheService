package com.rabbit.service.impl;

import com.rabbit.bean.HqlBean;
import com.rabbit.dao.IBaseDao;
import com.rabbit.dao.daoImpl.BaseDao;
import com.rabbit.entity.GoodsEntity;
import com.rabbit.service.IGoodService;
import com.rabbit.service.ILableService;
import com.rabbit.service.IListBean;
import com.rabbit.service.list.GoodsList;
import com.rabbit.util.RabbitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2017/3/3.
 */
@Service
@Scope(value = "prototype")
public class GoodService implements IGoodService {
    final private IListBean<GoodsEntity> goodsListBean;
    private final ILableService mILableService;
    private final IBaseDao<GoodsEntity> mGoodsEntityIBaseDao;

    @Autowired
    public GoodService(GoodsList goodsListBean, ILableService mILableService, IBaseDao<GoodsEntity> mGoodsEntityIBaseDao) {
        this.goodsListBean = goodsListBean;
        this.mILableService = mILableService;
        this.mGoodsEntityIBaseDao = mGoodsEntityIBaseDao;
    }
    public static void main(String[] args){
//        GoodService goodService = new GoodService(new GoodsList(new BaseDao<GoodsEntity>()));
//        IListBean<GoodsEntity> goodsListBean =  goodService.getTitleList("cat",1,2);
//        System.out.println(goodsListBean.getList().size());
    }

    @Override
    public IListBean<GoodsEntity> getAllList(int page, int lines) {
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and goodsDelete = ? ");
        hqlBean.setRulesHql(" order by goodsId desc ");
        hqlBean.addObject(GOODS_DELETE_FALSE);
        goodsListBean.init(hqlBean,page,lines);
        return goodsListBean;
    }

    @Override
    public IListBean<GoodsEntity> getHotList(int page, int lines) {
        return getStatusList(GOODS_STATUS_HOT,page,lines);
    }

    @Override
    public IListBean<GoodsEntity> getRecommandList(int page, int lines) {
        return getStatusList(GOODS_STATUS_RECOMMEND,page,lines);
    }

    @Override
    public IListBean<GoodsEntity> getTypeList(String types, int page, int lines) {
        return null;
    }

    @Override
    public IListBean<GoodsEntity> getTitleList(String title, int page, int lines) {
        return getGoodsByTitle(title.split("\\s+"),page,lines);
    }

    @Override
    public IListBean<GoodsEntity> getGoodsItem(int goodsId) {
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and goodsId = ? and goodsDelete = ?");
        hqlBean.setRulesHql(" order by goodsId desc ");
        hqlBean.addObject(goodsId);
        hqlBean.addObject(GOODS_DELETE_FALSE);
        goodsListBean.init(hqlBean,1,1);
        return goodsListBean;
    }

    @Override
    public IListBean<GoodsEntity> getStatusList(String type, int page, int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and status = ? and goodsDelete = ?");
        hqlBean.setRulesHql(" order by goodsId desc ");
        hqlBean.addObject(type);
        hqlBean.addObject(GOODS_DELETE_FALSE);
        goodsListBean.init(hqlBean,page,lines);
        return goodsListBean;
    }
    @Override
    public boolean addGoods(GoodsEntity goodsEntity){
        IBaseDao<GoodsEntity> goodsBaseDao = new BaseDao<>();
        try {
            goodsBaseDao.save(goodsEntity);
            RabbitLog.debug("保存 了一件 新的商品 :" + goodsEntity.getTitle());
            //同时添加新的标签
            mILableService.addOrSaveLable(goodsEntity.getTitle(),ILableService.LABLE_MARK_TITLE);
            return true;

        }catch (Exception e){
            RabbitLog.debug(" 保存商品 或者 添加标签 失败"+ goodsEntity.getTitle());
            return false;
        }
    }

    @Override
    public void updataGoods(GoodsEntity goodsEntity) throws Exception {
        mGoodsEntityIBaseDao.update(goodsEntity);
    }
    @Override
    public void updataGoods(List<GoodsEntity> goodsEntities) throws Exception {
        mGoodsEntityIBaseDao.update(goodsEntities);
    }

    private IListBean<GoodsEntity> getGoodsByTitle(String[] title,int page,int lines){
        HqlBean hqlBean = new HqlBean();
        StringBuffer buff = new StringBuffer();
        for(String list:title){
            buff.append( " and title like ? ");
            hqlBean.addObject('%'+list+'%');
        }
        buff.append( " and goodsDelete = ? " );
        hqlBean.addObject(GOODS_DELETE_FALSE);
        hqlBean.setInnerHql(buff.toString());
        hqlBean.setRulesHql(" order by goodsId desc ");
        goodsListBean.init(hqlBean,page,lines);
        return goodsListBean;
    }
}
