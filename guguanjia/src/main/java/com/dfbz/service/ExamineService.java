package com.dfbz.service;

import com.dfbz.entity.Examine;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ExamineService extends IService<Examine>{

    PageInfo<Examine> selectAll(Map<String,Object> params);

}
