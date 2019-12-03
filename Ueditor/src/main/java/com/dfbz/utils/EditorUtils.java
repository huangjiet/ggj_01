package com.dfbz.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author hjt
 * @description
 * @date 2019/11/18
 */
    @Component
    public class EditorUtils {


        @Value("${imgPath}")
        private String imgPath;

        @Value("${realPath}")
        private String realPath;

        /**
         * 图片上传:根据传入的图片封装对象upfile
         * 指定上传文件路径，自定义上传的文件名
         *
         * @return
         */
        public String uploadImg(MultipartFile upfile){

            String originalFilename = upfile.getOriginalFilename();
            String lastName = originalFilename.substring(originalFilename.lastIndexOf("."));//获取文件后缀
            //生成自定义文件名   ：   随机UUID产生
            String targetFilName = UUID.randomUUID().toString()+lastName;
            //设置存放文件夹进行文件上传   以及  服务器虚拟路径
            File dir = new File(realPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            String exec= null;

            try {
                upfile.transferTo(new File(dir,targetFilName));//文件上传处理
                //正确返回值处理
                exec = new JSONObject(resultMap("SUCCESS",originalFilename,upfile.getSize(),targetFilName,lastName,imgPath+targetFilName)).toString() ;
            } catch (IOException e) {
                e.printStackTrace();
                exec = new JSONObject(resultMap("FAIL",null,0,null,null,null)).toString();
            }

            return exec;
        }



        //{"state": "SUCCESS","original": "111.jpg","size": "124147","title": "1535961757878095151.jpg","type": ".jpg","url": "/1535961757878095151.jpg"}

        private Map<String,Object> resultMap(String state, String original, long size, String title, String type, String url){
            Map<String ,Object> result = new HashMap<>();
            result.put("state",state);
            result.put("original",original);
            result.put("size",size);
            result.put("title",title);
            result.put("type",type);
            result.put("url", url);
            return result;
        }


    }

