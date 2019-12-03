package com.dfbz.controller;


import com.dfbz.entity.AppVersion;
import com.dfbz.entity.Result;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("manager/app")
public class AppVersionController {

    @Autowired
    AppVersionService service;

    //加载index列表信息
    @RequestMapping("index")
    @ResponseBody
    public PageInfo<AppVersion> index(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        return service.selectAll(pageNum,pageSize);
    }

    @RequestMapping("toUpdate")
    @ResponseBody
    public AppVersion toUpdate(Long id){
        return  service.selectByPrimaryKey(id);
    }


    @RequestMapping("update")
    @ResponseBody
    public Result update(@RequestBody AppVersion appVersion){
        int i = service.updateByPrimaryKey(appVersion);
        Result result = new Result();
        if (i>0){
            result.setMsg("操作成功");
            result.setSuccess(true);
        }
        return result;

    }

    @RequestMapping("doDelete")
    @ResponseBody
    public Result doDelete(Long id){
        AppVersion appVersion = new AppVersion();
        appVersion.setId(id);
        appVersion.setUpdateDate(new Date());
        appVersion.setDelFlag("1");
        int delete = service.delete(appVersion);
        Result result = new Result();
        if (delete>0){
            result.setMsg("删除成功！");
            result.setSuccess(true);
        }
        return result;
    }



}
