package com.dfbz.dao;

import com.dfbz.entity.SysArea;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAreaMapper extends Mapper<SysArea> {


    @Select(" select sub.*,parent.name parentName " +
            " from " +
            " sys_area sub,sys_area parent " +
            " where " +
            " sub.parent_ids like CONCAT('%',#{aid},'%') " +
            " and " +
            " sub.parent_id=parent.id")
    List<SysArea> selectByPid(Long aid);

    @InsertProvider(type = SysAreaProvider.class,method = "insertBatch")
    int insertBatch(@Param("sysAreas") List<SysArea> sysAreas);

    //根据当前区域的id更新所有其子级区域的parentIds
    @Update("update  " +
            " sys_area " +
            "set " +
            " parent_ids = REPLACE(parent_ids,#{oldParentIds},#{parentIds}) " +
            "where " +
            " parent_ids like concat('%',#{id},'%')")
    int updateParentIds(SysArea sysArea);




    //根据父区域id查找所有区域
    @Select("select sub.*,parent.name parentName " +
            "from " +
            " sys_area sub,sys_area parent " +
            "where " +
            " sub.id = #{aid} " +
            "and " +
            " sub.parent_id=parent.id")
    SysArea selectByAid(long aid);
}
