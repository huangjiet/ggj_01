package com.dfbz.controller;

import com.dfbz.entity.Examine;
import com.dfbz.service.ExamineService;
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
 * @date 2019/11/20
 */

@RestController
@RequestMapping("manager/examine")
public class ExamineController {
    @Autowired
    ExamineService service;

    @RequestMapping("index")
    public PageInfo<Examine> index( @RequestBody Map<String,Object> params){
        return service.selectAll(params);
    }

   @RequestMapping("toUpdate")
    @ResponseBody
    public Examine toUpdate(Long id){
        return (Examine) service.selectByPrimaryKey(id);
    }
}
