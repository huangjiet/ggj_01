package com.dfbz.service;

import com.dfbz.entity.Statute;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface StatuteService extends IService<Statute> {
    PageInfo<Statute> selectByCondition(Map<String, Object> params);
}
