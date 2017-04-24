package com.rabbit.service.impl;

import com.rabbit.bean.GsonSort;
import com.rabbit.bean.GsonSortApply;
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
        hqlBean.setInnerHql(" and goodsDelete = ? and stock > ?");
        hqlBean.setRulesHql(" order by goodsId desc ");
        hqlBean.addObject(GOODS_DELETE_FALSE);
        hqlBean.addObject(0);
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
        hqlBean.setInnerHql(" and status = ? and goodsDelete = ? and stock > ?");
        hqlBean.setRulesHql(" order by goodsId desc ");
        hqlBean.addObject(type);
        hqlBean.addObject(GOODS_DELETE_FALSE);
        hqlBean.addObject(0);
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

    public static final String DESC = "降序";
    public static final String ASC = "升序";

    public static final String BUY = "购买量";
    public static final String SEE = "访问量";
    public static final String COMMENT = "评价数";
    public static final String PRICE = "价格";
    public static final String SCOLE = "分数";
    public static final String DATE = "日期";
    //排序方式
    @Override
    public GsonSort getSort(){
        List<String> dataSort =  new ArrayList<>();
        List<String> dataType =  new ArrayList<>();
        GsonSort gsonSort = new GsonSort();
        dataSort.add(DESC);
        dataSort.add(ASC);

        dataType.add(BUY);
        dataType.add(SEE);
        dataType.add(COMMENT);
        dataType.add(PRICE);
        dataType.add(SCOLE);
        dataType.add(DATE);


        gsonSort.setDataSort(dataSort);
        gsonSort.setDataType(dataType);
        return gsonSort;
    }
    //排序
    @Override
    public List<GoodsEntity> getGoodsHighSearch(GsonSortApply gsonSortApply){
        String headHql = "select new com.rabbit.entity.GoodsEntity(g.goodsId,g.type,g.price,g.title,g.picture," +
                " g.sales,g.stock,g.status,g.views,g.goodsDelete, g.pictureGroup)" +
                "from GoodsEntity as g left join CommentEntity as c  on g.goodsId = c.goodsId ";

        String headSql = "select g.goodsId,g.type,g.price,g.title,g.picture," +
                " g.sales,g.stock,g.status,g.views,g.goodsDelete, g.pictureGroup " +
                "from goods as g left join comment as c  on g.goodsId = c.goodsId ";

        StringBuffer hqlBuffer = new StringBuffer();
        List<Object> param = new ArrayList<>();
        hqlBuffer.append(headSql);
        {//基本查询 标题
            hqlBuffer.append(" where g.title like ? ");
            param.add('%'+gsonSortApply.getTitle()+'%');
        }
        {
            if(null!=gsonSortApply.getLable()) {
                for (String mark : gsonSortApply.getLable()) {
                    if (!"".equals(mark)) {
                        hqlBuffer.append(" and (type like ?  or type like ? )");
                        param.add('%' + "#" + mark + '%');
                        param.add('%' + mark + "#" + '%');
                        RabbitLog.debug(" mark :  "+mark);
                    }
                }
            }
        }
        {
            hqlBuffer.append(" group by g.goodsId ");
        }
        switch (gsonSortApply.getType()){
            case BUY:
                hqlBuffer.append(" order by g.sales  ");
                break;
            case SEE:
                hqlBuffer.append(" order by g.views ");
                break;
            case PRICE:
                hqlBuffer.append(" order by g.price ");
                break;
            case DATE:
                hqlBuffer.append(" order by g.goodsId ");
                break;
            case COMMENT:
                hqlBuffer.append(" order by (select count(*) from comment as co where co.goodsId = g.goodsId) ");
                break;
            case SCOLE:
                hqlBuffer.append("  order by (select avg(start) from comment as co where co.goodsId = g.goodsId group by co.goodsId ) ");
                break;
            default:
                hqlBuffer.append(" order by g.goodsId ");
                break;

        }
        switch (gsonSortApply.getSort()){
            case DESC:
                hqlBuffer.append(" desc ");
                break;
            case ASC:
                hqlBuffer.append(" asc ");
                break;
            default:
                hqlBuffer.append(" desc ");
                break;
        }
        hqlBuffer.append(" limit "+(gsonSortApply.getPage()-1)*gsonSortApply.getLines()+","+gsonSortApply.getLines());

        RabbitLog.debug("搜索标题 :"+gsonSortApply.getTitle()+" 排序类型 :"+gsonSortApply.getType()+" 排序方式 :"+gsonSortApply.getSort());

        try{
             List<Object> list = mGoodsEntityIBaseDao.executeSQL(hqlBuffer.toString(),param.toArray());
             RabbitLog.debug("成功完成高级查询 page:"+gsonSortApply.getPage());
             List<GoodsEntity> goodsEntities = new ArrayList<>();
//            g.goodsId,  g.type,    g.price,   g.title,   g.picture," +
//            " g.sales,   g.stock,    g.status,   g.views,   g.goodsDelete,   g.pictureGroup"
             for(Object o:list){
                 GoodsEntity goodsEntity = new GoodsEntity();
                 goodsEntity.setGoodsId((int)((Object[]) o)[0]);
                 goodsEntity.setType((String)((Object[]) o)[1]);
                 double rs = (float)((Object[]) o)[2];
                 goodsEntity.setPrice(rs);
                 goodsEntity.setTitle((String)((Object[]) o)[3]);
                 goodsEntity.setPicture((String)((Object[]) o)[4]);
                 goodsEntity.setSales((int)((Object[]) o)[5]);
                 goodsEntity.setStock((int)((Object[]) o)[6]);
                 goodsEntity.setStatus((String)((Object[]) o)[7]);
                 goodsEntity.setViews((int)((Object[]) o)[8]);
                 goodsEntity.setGoodsDelete((int)((Object[]) o)[9]);
                 goodsEntity.setPictureGroup((String)((Object[]) o)[10]);
                 goodsEntities.add(goodsEntity);
             }


             return goodsEntities;
         }catch (Exception e){
             e.printStackTrace();
             return null;
         }
    }



    private IListBean<GoodsEntity> getGoodsByTitle(String[] title,int page,int lines){
        HqlBean hqlBean = new HqlBean();
        StringBuffer buff = new StringBuffer();
        for(String list:title){
            buff.append( " and title like ? ");
            hqlBean.addObject('%'+list+'%');
        }
        buff.append( " and goodsDelete = ? and stock > ?" );
        hqlBean.addObject(GOODS_DELETE_FALSE);
        hqlBean.addObject(0);
        hqlBean.setInnerHql(buff.toString());
        hqlBean.setRulesHql(" order by goodsId desc ");
        goodsListBean.init(hqlBean,page,lines);
        return goodsListBean;
    }
}
