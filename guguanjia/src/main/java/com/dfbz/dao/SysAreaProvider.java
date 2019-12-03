package com.dfbz.dao;

import com.dfbz.entity.SysArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hjt
 * @description
 * @date 2019/11/21
 */
public class SysAreaProvider {

    public String  insertBatch(@Param("sysAreas") List<SysArea> sysAreas){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `guguanjia`.`sys_area`( `parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, `create_date`, " +
                "`update_by`, `update_date`, `remarks`, `del_flag`, `icon`) VALUES ");

        for (int i = 0; i < sysAreas.size(); i++) {
            sb.append("(");
            sb.append("#{sysAreas["+i+"].parentId}," +
                    "#{sysAreas["+i+"].parentIds}," +
                    "#{sysAreas["+i+"].code}," +
                    "#{sysAreas["+i+"].name}," +
                    "#{sysAreas["+i+"].type}," +
                    "#{sysAreas["+i+"].createBy}," +
                    "#{sysAreas["+i+"].createDate}," +
                    "#{sysAreas["+i+"].updateBy}," +
                    "#{sysAreas["+i+"].updateDate}," +
                    "#{sysAreas["+i+"].remarks}," +
                    "#{sysAreas["+i+"].delFlag}," +
                    "#{sysAreas["+i+"].icon}" );
            sb.append("),");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
        return sb.toString();



    }
}
