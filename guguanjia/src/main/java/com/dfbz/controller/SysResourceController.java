package com.dfbz.controller;

import com.dfbz.entity.SysResource;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hjt
 * @description
 * @date 2019/11/30
 */

@RestController
@RequestMapping("manager/menu")
public class SysResourceController {
    @Autowired
    SysResourceService sysResourceService;


    @RequestMapping("selectAll")
    public List<SysResource> selectAll(){
        return sysResourceService.selectAll();
    }

    @RequestMapping("selectByRid")
    public List<SysResource> selectByRid(long rid){
        return sysResourceService.selectByRid(rid);
    }

    @RequestMapping("selectAllByUid")
    public List<SysResource> selectAllByUid(long uid){
        return sysResourceService.selectAllByUid(uid);
    }
}
