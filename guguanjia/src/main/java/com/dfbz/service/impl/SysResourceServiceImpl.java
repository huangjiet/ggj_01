package com.dfbz.service.impl;

import com.dfbz.dao.SysResourceMapper;
import com.dfbz.dao.SysRoleMapper;
import com.dfbz.entity.SysResource;
import com.dfbz.entity.SysRole;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hjt
 * @description
 * @date 2019/11/30
 */

@Service
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    SysResourceService sysResourceService;

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysResourceMapper resourceMapper;

    @Override
    @Cacheable(value = "resourceCache",key = "'SysResourceServiceImpl:selectAll'")
    public List<SysResource> selectAll() {
        return super.selectAll();
    }

    @Override
    public List<SysResource> selectByRid(long rid) {
        return sysResourceService.selectByRid(rid);
    }

    @Override
    public List<SysResource> selectAllByUid(long uid){
        List<SysRole> sysRoles = roleMapper.selectRoleByUid(uid);
        List<SysResource> userResources = null;
        if(sysRoles!=null){
            //遍历所有角色，查询每个角色的权限   去重
            Set<SysResource> set = new HashSet<>();
            for (SysRole sysRole : sysRoles) {
                List<SysResource> roleResources = resourceMapper.selectByRid(sysRole.getId());
                set.addAll(roleResources);//去重
            }
            userResources = new ArrayList<>();
            userResources.addAll(set);
        }
        return userResources;
    }
}
