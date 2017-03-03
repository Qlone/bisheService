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
 * Created by weina on 2017/3/3.
 */
@Entity
@Repository
@Table(name = "lable", schema = "bishe", catalog = "")
public class LableEntity {
    private Integer lableId;
    private String text;
    private Integer view;
    private String mark;

    @Id
    @Column(name = "lableId", nullable = false)
    public Integer getLableId() {
        return lableId;
    }

    public void setLableId(Integer lableId) {
        this.lableId = lableId;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 100)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "view", nullable = true)
    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    @Basic
    @Column(name = "mark", nullable = true, length = 20)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LableEntity that = (LableEntity) o;

        if (lableId != null ? !lableId.equals(that.lableId) : that.lableId != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (view != null ? !view.equals(that.view) : that.view != null) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lableId != null ? lableId.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (view != null ? view.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }
}
