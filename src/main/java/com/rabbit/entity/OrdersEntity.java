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
import java.sql.Timestamp;

/**
 * Created by weina on 2017/3/10.
 */
@Entity
@Repository
@Table(name = "orders", schema = "bishe", catalog = "")
public class OrdersEntity {
    private Integer mOrdersId;
    private Integer mGoodId;
    private String mAddress;
    private String mPhone;
    private String mPicture;
    private String mTitle;
    private Double mPrice;
    private Timestamp mCreateTime;
    private Timestamp mPaidTime;
    private String mStatus;

    @Id
    @Column(name = "ordersId", nullable = false)
    public Integer getOrdersId() {
        return mOrdersId;
    }

    public void setOrdersId(Integer ordersId) {
        mOrdersId = ordersId;
    }

    @Basic
    @Column(name = "goodId", nullable = true)
    public Integer getGoodId() {
        return mGoodId;
    }

    public void setGoodId(Integer goodId) {
        mGoodId = goodId;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 200)
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    @Basic
    @Column(name = "picture", nullable = true, length = 300)
    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String picture) {
        mPicture = picture;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    @Basic
    @Column(name = "createTime", nullable = true)
    public Timestamp getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(Timestamp createTime) {
        mCreateTime = createTime;
    }

    @Basic
    @Column(name = "paidTime", nullable = true)
    public Timestamp getPaidTime() {
        return mPaidTime;
    }

    public void setPaidTime(Timestamp paidTime) {
        mPaidTime = paidTime;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (mOrdersId != null ? !mOrdersId.equals(that.mOrdersId) : that.mOrdersId != null) return false;
        if (mGoodId != null ? !mGoodId.equals(that.mGoodId) : that.mGoodId != null) return false;
        if (mAddress != null ? !mAddress.equals(that.mAddress) : that.mAddress != null) return false;
        if (mPhone != null ? !mPhone.equals(that.mPhone) : that.mPhone != null) return false;
        if (mPicture != null ? !mPicture.equals(that.mPicture) : that.mPicture != null) return false;
        if (mTitle != null ? !mTitle.equals(that.mTitle) : that.mTitle != null) return false;
        if (mPrice != null ? !mPrice.equals(that.mPrice) : that.mPrice != null) return false;
        if (mCreateTime != null ? !mCreateTime.equals(that.mCreateTime) : that.mCreateTime != null) return false;
        if (mPaidTime != null ? !mPaidTime.equals(that.mPaidTime) : that.mPaidTime != null) return false;
        if (mStatus != null ? !mStatus.equals(that.mStatus) : that.mStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mOrdersId != null ? mOrdersId.hashCode() : 0;
        result = 31 * result + (mGoodId != null ? mGoodId.hashCode() : 0);
        result = 31 * result + (mAddress != null ? mAddress.hashCode() : 0);
        result = 31 * result + (mPhone != null ? mPhone.hashCode() : 0);
        result = 31 * result + (mPicture != null ? mPicture.hashCode() : 0);
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mPrice != null ? mPrice.hashCode() : 0);
        result = 31 * result + (mCreateTime != null ? mCreateTime.hashCode() : 0);
        result = 31 * result + (mPaidTime != null ? mPaidTime.hashCode() : 0);
        result = 31 * result + (mStatus != null ? mStatus.hashCode() : 0);
        return result;
    }
}
