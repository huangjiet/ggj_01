package com.dfbz.controller;

import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/11/27
 */

@RestController
@RequestMapping("manager/user")
public class SysUserController {

    @Autowired
    SysUserService service;


    @RequestMapping("index")
    public PageInfo<SysUser> index(@RequestBody Map<String,Object> params){
        return service.selectByCondition(params);
    }


    @RequestMapping("toUpdate")
    public SysUser toUpdate(long id){
        return service.selectByUid(id);
    }

    @RequestMapping("selectByRid")
    public List<SysUser> selectByRid(long rid){
        return service.selectByRid(rid);
    }

    @RequestMapping("selectNoRole")
    public List<SysUser> selectNoRole(long rid,long oid){
        return service.selectNoRole(rid,oid);
    }

}
