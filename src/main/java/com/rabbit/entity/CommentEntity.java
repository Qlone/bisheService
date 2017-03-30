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
import java.util.Date;

/**
 * Created by weina on 2017/3/28.
 */
@Entity
@Repository
@Table(name = "comment", schema = "bishe", catalog = "")
public class CommentEntity {
    private Integer mCommentId;
    private Integer mOrderId;
    private Integer mUserId;
    private Integer mGoodsId;
    private Date mCommentData;
    private String mContext;
    private Integer mStart;
    private String mUserName;

    @Id
    @Column(name = "commentId", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getCommentId() {
        return mCommentId;
    }

    public void setCommentId(Integer commentId) {
        mCommentId = commentId;
    }

    @Basic
    @Column(name = "orderId", nullable = true)
    public Integer getOrderId() {
        return mOrderId;
    }

    public void setOrderId(Integer orderId) {
        mOrderId = orderId;
    }

    @Basic
    @Column(name = "userId", nullable = true)
    public Integer getUserId() {
        return mUserId;
    }

    public void setUserId(Integer userId) {
        mUserId = userId;
    }

    @Basic
    @Column(name = "goodsId", nullable = true)
    public Integer getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(Integer goodsId) {
        mGoodsId = goodsId;
    }

    @Basic
    @Column(name = "commentData", nullable = true)
    public Date getCommentData() {
        return mCommentData;
    }

    public void setCommentData(Date commentData) {
        mCommentData = commentData;
    }

    @Basic
    @Column(name = "context", nullable = true, length = 255)
    public String getContext() {
        return mContext;
    }

    public void setContext(String context) {
        mContext = context;
    }

    @Basic
    @Column(name = "start", nullable = true)
    public Integer getStart() {
        return mStart;
    }

    public void setStart(Integer start) {
        mStart = start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (mCommentId != null ? !mCommentId.equals(that.mCommentId) : that.mCommentId != null) return false;
        if (mOrderId != null ? !mOrderId.equals(that.mOrderId) : that.mOrderId != null) return false;
        if (mUserId != null ? !mUserId.equals(that.mUserId) : that.mUserId != null) return false;
        if (mGoodsId != null ? !mGoodsId.equals(that.mGoodsId) : that.mGoodsId != null) return false;
        if (mCommentData != null ? !mCommentData.equals(that.mCommentData) : that.mCommentData != null) return false;
        if (mContext != null ? !mContext.equals(that.mContext) : that.mContext != null) return false;
        if (mStart != null ? !mStart.equals(that.mStart) : that.mStart != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mCommentId != null ? mCommentId.hashCode() : 0;
        result = 31 * result + (mOrderId != null ? mOrderId.hashCode() : 0);
        result = 31 * result + (mUserId != null ? mUserId.hashCode() : 0);
        result = 31 * result + (mGoodsId != null ? mGoodsId.hashCode() : 0);
        result = 31 * result + (mCommentData != null ? mCommentData.hashCode() : 0);
        result = 31 * result + (mContext != null ? mContext.hashCode() : 0);
        result = 31 * result + (mStart != null ? mStart.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 255)
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}
