package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/11/24
 */
public class SysOfficeProvider {

    public String selectByCondition(Map<String, Object> params){

        StringBuilder sb = new StringBuilder();
        sb.append("select so.*,sa.name areaName from  " +
                " sys_office so,sys_area sa " +
                "where " +
                " so.del_flag=0 " +
                "and " +
                " so.area_id=sa.id ");
        if(params.containsKey("name")&&!StringUtils.isEmpty(params.get("name"))){
            sb.append(" and so.name=#{name} ");
        }

        System.out.println(sb.toString());
        return sb.toString();

    }
}
