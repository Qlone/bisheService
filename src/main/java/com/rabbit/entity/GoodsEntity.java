/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by weina on 2017/3/2.
 */
@Entity
@Repository
@Table(name = "goods", schema = "bishe", catalog = "")
public class GoodsEntity {
    private Integer goodsId;
    private String type;
    private Double price;
    private String title;
    private String picture;
    private Integer sales;
    private Integer stock;
    private String status;
    private Integer views;
    private Integer goodsDelete;
    private String mPictureGroup;

    @Basic
    @Column(name = "type", nullable = true, length = 200)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "goodsId", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "picture", nullable = true, length = 300)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "sales", nullable = true)
    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    @Basic
    @Column(name = "stock", nullable = true)
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 100)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "views", nullable = true)
    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsEntity that = (GoodsEntity) o;

        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (sales != null ? !sales.equals(that.sales) : that.sales != null) return false;
        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (views != null ? !views.equals(that.views) : that.views != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId != null ? goodsId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (sales != null ? sales.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (views != null ? views.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "goodsDelete", nullable = true)
    public Integer getGoodsDelete() {
        return goodsDelete;
    }

    public void setGoodsDelete(Integer goodsDelete) {
        this.goodsDelete = goodsDelete;
    }

    @Basic
    @Column(name = "pictureGroup", nullable = true, length = -1)
    public String getPictureGroup() {
        return mPictureGroup;
    }

    public void setPictureGroup(String pictureGroup) {
        mPictureGroup = pictureGroup;
    }
}
