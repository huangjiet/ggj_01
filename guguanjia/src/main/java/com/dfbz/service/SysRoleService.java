package com.dfbz.service;

import com.dfbz.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


public interface SysRoleService extends IService<SysRole> {

    PageInfo<SysRole> selectByCondition(Map<String, Object> params);

    SysRole selectOneByCondition(long uid);

    int updateByUids(long rid,long... uids);

    int insertBatch(List<Long> cids,long rid);
}
