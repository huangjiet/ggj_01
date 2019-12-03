package com.dfbz.controller;

import com.dfbz.entity.Qualification;
import com.dfbz.entity.Result;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/11/16
 */
@RestController //替代Controller
@RequestMapping("manager/qualification")
public class QualificationController {

    @Autowired
    QualificationService service;

    @RequestMapping("index")
    public PageInfo<Qualification> index(@RequestBody Map<String, Object> params){
            return service.selectByCondition(params);
    }

    @RequestMapping("toUpdate")
    @ResponseBody
    public Qualification toUpdate(Long id){
            return service.selectByPrimaryKey(id);
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(@RequestBody Qualification qualification){
        qualification.setAddress(null);
        int i = service.updateByPrimaryKeySelective(qualification);
        Result result = new Result();
        if(i>0){
            result.setMsg("操作成功");
            result.setSuccess(true);
        }
        return result;
    }
}
