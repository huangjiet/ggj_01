package com.dfbz.dao;

import com.dfbz.entity.SysOffice;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/11/20
 */
public interface SysOfficeMapper extends Mapper<SysOffice> {


    @SelectProvider(type = SysOfficeProvider.class,method = "selectByCondition")
    List<SysOffice> selectByCondition(Map<String, Object> params);

    @Select("select so.*,sa.name areaName from  " +
            " sys_office so,sys_area sa " +
            " where " +
            " so.id=#{oid} " +
            " and " +
            " so.del_flag=0 " +
            " and " +
            " so.area_id=sa.id ")
    SysOffice selectByOid(long oid);
}
