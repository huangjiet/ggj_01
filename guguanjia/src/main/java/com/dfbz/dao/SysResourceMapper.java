package com.dfbz.dao;

import com.dfbz.entity.SysResource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {

   /* @Select("select se.* " +
            " from " +
            " sys_role sr,sys_role_resource srr,sys_resource se " +
            " where " +
            " sr.id=#{rid} " +
            " and " +
            " sr.id=srr.role_id " +
            " and " +
            " se.id=srr.resource_id")
    List<SysResource> selectByRid(long rid);*/

    @Select("select se.* from sys_role sr,sys_role_resource srr,sys_resource se where sr.id=#{rid} and sr.id=srr.role_id and se.id=srr.resource_id")
    List<SysResource> selectByRid(long rid);
}
