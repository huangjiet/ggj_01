package com.dfbz.dao;

import com.dfbz.entity.AppVersion;
import org.apache.ibatis.jdbc.SQL;

public class AppVersionSqlProvider {

    public String insertSelective(AppVersion record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("app_version");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getPlatform() != null) {
            sql.VALUES("platform", "#{platform,jdbcType=INTEGER}");
        }
        
        if (record.getVersionNo() != null) {
            sql.VALUES("version_no", "#{versionNo,jdbcType=VARCHAR}");
        }
        
        if (record.getForceUpdate() != null) {
            sql.VALUES("force_update", "#{forceUpdate,jdbcType=INTEGER}");
        }
        
        if (record.getDownPath() != null) {
            sql.VALUES("down_path", "#{downPath,jdbcType=VARCHAR}");
        }
        
        if (record.getSize() != null) {
            sql.VALUES("size", "#{size,jdbcType=REAL}");
        }
        
        if (record.getAppExplain() != null) {
            sql.VALUES("app_explain", "#{appExplain,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.VALUES("update_date", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelFlag() != null) {
            sql.VALUES("del_flag", "#{delFlag,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateBy() != null) {
            sql.VALUES("create_by", "#{createBy,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AppVersion record) {
        SQL sql = new SQL();
        sql.UPDATE("app_version");
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{platform,jdbcType=INTEGER}");
        }
        
        if (record.getVersionNo() != null) {
            sql.SET("version_no = #{versionNo,jdbcType=VARCHAR}");
        }
        
        if (record.getForceUpdate() != null) {
            sql.SET("force_update = #{forceUpdate,jdbcType=INTEGER}");
        }
        
        if (record.getDownPath() != null) {
            sql.SET("down_path = #{downPath,jdbcType=VARCHAR}");
        }
        
        if (record.getSize() != null) {
            sql.SET("size = #{size,jdbcType=REAL}");
        }
        
        if (record.getAppExplain() != null) {
            sql.SET("app_explain = #{appExplain,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.SET("update_date = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelFlag() != null) {
            sql.SET("del_flag = #{delFlag,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateBy() != null) {
            sql.SET("create_by = #{createBy,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}