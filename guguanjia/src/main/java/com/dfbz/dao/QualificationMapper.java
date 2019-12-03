package com.dfbz.dao;

/**
 * @author hjt
 * @description
 * @date 2019/11/16
 */
import com.dfbz.entity.Qualification;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface QualificationMapper extends Mapper<Qualification> {

    //解决map返回null不显示和重名问题:
    //1.map返回null需要设置sqlSessionFactoryBean 返回map的null不忽略  configuration.setCallSettersOnNulls(true)
    //2.重名问题可以设置别名
    @Select(" SELECT   " +
            "  uu.`name` uploadUserName,cu.`name`  checkUserName" +
            " FROM   " +
            "  qualification qu  " +
            " LEFT JOIN  " +
            "  sys_user uu  " +
            " ON  " +
            "  qu.upload_user_id=uu.id  " +
            " LEFT JOIN  " +
            "  sys_user cu  " +
            " ON  " +
            "  qu.check_user_id=cu.id  " +
            " WHERE  " +
            "  qu.id=#{id}  " +
            " ")
    Map<String,Object> selectNames(Long id);
}
