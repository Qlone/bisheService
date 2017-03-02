/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.util;

import com.rabbit.service.impl.CheckUser;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by weina on 2017/3/1.
 */
public class RabbitLog {
    public static boolean sDebug = true;

    private static String sClassName, sMethodName;
    private static int sLineNumber;

    /**
     * 获取日志发生位置信息
     * @param stackTraceElements
     */
    private static void getMethodInfo(StackTraceElement[] stackTraceElements) {
        sClassName = stackTraceElements[1].getFileName();
        sMethodName = stackTraceElements[1].getMethodName();
        sLineNumber = stackTraceElements[1].getLineNumber();
    }

    /**
     * 日志封装.
     * @param message
     * @return
     */
    private static String createLog(String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(sMethodName)
                .append("(")
                .append(sClassName)
                .append(":")
                .append(sLineNumber).append(") ")
                .append(message);
        Date date = new Date();
        SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String name = sFormatter.format(date);
        String path= CheckUser.getRequest().getSession().getServletContext().getRealPath("");

        AppendContentToFile.method1(path+"/log/"+name+".txt",builder.toString());
        return builder.toString();
    }

    /**
     * 产生日志调用
     * @param msg
     */
    public static void debug(String msg) {
        if (!sDebug) return;
        getMethodInfo(new Throwable().getStackTrace());
        System.out.println(createLog(msg));
    }

    public static void debug(Object msg) {
        if (!sDebug) return;
        getMethodInfo(new Throwable().getStackTrace());
        System.out.println(createLog(msg.toString()));
    }

    /**
     * 开启日志，默认开启
     */
    public void onDebug() {
        sDebug = true;
    }

    /**
     * 关闭日志
     */
    public void offDebug() {
        sDebug = false;
    }
}
