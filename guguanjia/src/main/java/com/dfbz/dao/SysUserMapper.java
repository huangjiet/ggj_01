package com.dfbz.dao;

/**
 * @author hjt
 * @description
 * @date 2019/11/16
 */
import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface SysUserMapper extends Mapper<SysUser> {

    @Results({
            @Result(column = "id",property = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.dfbz.dao.SysRoleMapper.selectRoleByUid"))
    })
    @SelectProvider(type = SysUserMapperProvider.class,method = "selectByCondition")
    List<SysUser> selectByCondition(Map<String, Object> params);



    @Select("SELECT su.* ,sur.*,sr.* from "+
            "  sys_user su  " +
            " LEFT JOIN  " +
            "  sys_user_role sur  " +
            "ON  " +
            "  su.id=sur.user_id  " +
            "LEFT JOIN  " +
            "  sys_role sr  " +
            "ON  " +
            "  sr.id=sur.role_id  " +
            "where  " +
            " su.id=#{uid} "+
            " and "+
            " sur.del_flag=0 "
    )
    SysUser selectByUid(long uid);


    @Select("select " +
            " su.* " +
            "from " +
            " sys_role sr,sys_user_role sur,sys_user su " +
            "where " +
            " sr.id=#{rid} " +
            "and " +
            " sr.id=sur.role_id " +
            "and " +
            " su.id=sur.user_id " +
            "and" +
            "  sur.del_flag=0 ")
    List<SysUser> selectByRid(long rid);

    @Select("select " +
            " * " +
            "from " +
            " sys_user " +
            "where " +
            " office_id=#{oid} " +
            "and " +
            " id  " +
            "not in " +
            "( " +
            "select " +
            " sur.user_id " +
            "from " +
            " sys_role sr,sys_user_role sur " +
            "where " +
            " sr.id=#{rid} " +
            "and " +
            " sr.id=sur.role_id " +
            ")")
    List<SysUser> selectNoRole(@Param("rid") long rid,@Param("oid")long oid);

}
