package com.dfbz.service;

import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    PageInfo<SysUser> selectByCondition(Map<String, Object> params);

    SysUser selectByUid(long uid);


    List<SysUser> selectByRid(long rid);

    List<SysUser> selectNoRole(long rid, long oid);

}
