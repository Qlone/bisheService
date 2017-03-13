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
 * Created by weina on 2017/3/10.
 */
@Entity
@Repository
@Table(name = "address", schema = "bishe", catalog = "")
public class AddressEntity {
    private Integer mAddressId;
    private String mAddress;
    private String mPhone;
    private Integer mUserId;

    @Id
    @Column(name = "addressId", nullable = false)
    public Integer getAddressId() {
        return mAddressId;
    }

    public void setAddressId(Integer addressId) {
        mAddressId = addressId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (mAddressId != null ? !mAddressId.equals(that.mAddressId) : that.mAddressId != null) return false;
        if (mAddress != null ? !mAddress.equals(that.mAddress) : that.mAddress != null) return false;
        if (mPhone != null ? !mPhone.equals(that.mPhone) : that.mPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mAddressId != null ? mAddressId.hashCode() : 0;
        result = 31 * result + (mAddress != null ? mAddress.hashCode() : 0);
        result = 31 * result + (mPhone != null ? mPhone.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "userId", nullable = true)
    public Integer getUserId() {
        return mUserId;
    }

    public void setUserId(Integer userId) {
        mUserId = userId;
    }
}
