package com.dfbz.controller;

import com.baidu.ueditor.ActionEnter;
import com.dfbz.utils.EditorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author hjt
 * @description
 * @date 2019/11/18
 */
@Controller
@RequestMapping("ueditor")
public class EditorController {
    @Autowired
    EditorUtils editorUtils;

    @RequestMapping("execute")
    @ResponseBody
    public String doExecute(String action , HttpServletResponse response, HttpServletRequest request, @RequestParam(name="upfile",required = false) MultipartFile upfile) throws UnsupportedEncodingException {
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String exec = "";


        if("config".equals(action) ){
            exec = new ActionEnter(request, rootPath).exec();
        }else if("uploadimage".equals(action)) {
            exec =  editorUtils.uploadImg(upfile);
        }


        return exec;


    }




}
