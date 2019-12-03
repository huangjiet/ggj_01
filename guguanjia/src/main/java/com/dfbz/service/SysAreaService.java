package com.dfbz.service;

import com.dfbz.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;


public interface SysAreaService extends IService<SysArea> {

    PageInfo<SysArea> selectByPage(Map<String,Object> params);



    OutputStream getListOutputStream(OutputStream outputStream);

    int importExcel(InputStream inputStream);

    SysArea selectByAid(long aid);

    int updateArea(SysArea sysArea);

}
