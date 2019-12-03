package com.dfbz.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.dao.SysAreaMapper;
import com.dfbz.entity.SysArea;
import com.dfbz.listener.SysAreaListener;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/11/21
 */

@Service
@Transactional
public class SysAreaServiceImpl extends ServiceImpl<SysArea> implements SysAreaService {

    @Autowired
    SysAreaMapper areaMapper;

    @Override
    public PageInfo<SysArea> selectByPage(Map<String,Object> params) {

        if (StringUtils.isEmpty( params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageInfo<SysArea> pageInfo=null;
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        if (params.containsKey("aid")&&!StringUtils.isEmpty(params.get("aid"))){
            int id = (Integer) params.get("aid");
            List<SysArea> list = areaMapper.selectByPid((long)id);
            pageInfo = new PageInfo<>(list);

        }else if (params.containsKey("areaName")&&!StringUtils.isEmpty("areaName")){
            //根据姓名查询
           /* SysArea sysArea = new SysArea();
            sysArea.setName((String) params.get("areaName"));
            List<SysArea> list = areaMapper.select(sysArea);
            pageInfo = new PageInfo<>(list);*/
        }else {
            /*List<SysArea> list = areaMapper.selectAll();
            pageInfo = new PageInfo<>(list);*/
        }

        return pageInfo;
    }

    @Override
    public OutputStream getListOutputStream(OutputStream outputStream) {

        List<SysArea> sysAreas = mapper.selectAll();


        ExcelWriter excelWriter = EasyExcel.write(outputStream,SysArea.class).build();

        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();

        excelWriter.write(sysAreas,writeSheet);

        excelWriter.finish();

        return outputStream;
    }

    @Override
    public int importExcel(InputStream inputStream) {
        int result = 0;
        ExcelReader excelReader = EasyExcel.read(inputStream, SysArea.class, new SysAreaListener(areaMapper)).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
        result ++;

        return result;
    }

    @Override
    public SysArea selectByAid(long aid) {
        SysArea sysArea = areaMapper.selectByAid(aid);
        sysArea.setOldParentIds(sysArea.getParentIds());//给区域绑定旧parentIds
        return areaMapper.selectByAid(aid);
    }

    @Override
    public int updateArea(SysArea sysArea) {

        int result = areaMapper.updateByPrimaryKey(sysArea);

        if (!sysArea.getOldParentIds().equals(sysArea.getParentIds())){

            areaMapper.updateParentIds(sysArea);
            result++;
        }
        return result;
    }


}
