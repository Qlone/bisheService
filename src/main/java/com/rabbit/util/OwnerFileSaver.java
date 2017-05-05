/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.rabbit.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * Created by fallb on 2016/12/9.
 */
public class OwnerFileSaver {
    public static String IP = "http://192.168.137.1:8080";

    public static String saveImage(MultipartFile multipartFile,String context) throws IOException {
        File root = new File(context);
        String[] splitFile = multipartFile.getOriginalFilename().split("\\.");
        String fileSuffix = splitFile[splitFile.length-1];
        String pathPrefix = "resources/images/photos/";
        String fileName = pathPrefix+UUID.randomUUID().toString()+"."+fileSuffix;

        File file = new File(root,fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            file.createNewFile();
        }

        OutputStream out = new FileOutputStream(file);
        InputStream in = multipartFile.getInputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = in.read(buffer))!=-1){
            out.write(buffer,0,length);
        }
        out.flush();
        in.close();
        out.close();
        return IP+"/"+fileName;
    }

}
