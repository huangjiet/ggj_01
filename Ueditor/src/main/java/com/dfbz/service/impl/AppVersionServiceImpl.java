package com.dfbz.service.impl;


import com.dfbz.dao.AppVersionMapper;
import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppVersionServiceImpl extends ServiceImpl<AppVersion> implements AppVersionService {

    @Autowired
    AppVersionMapper appVersionMapper;

    @Override
    public PageInfo<AppVersion> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//开启分页拦截功能

        AppVersion appVersion = new AppVersion();
        appVersion.setDelFlag("0");//不是0的不显示
        List<AppVersion> appVersions = mapper.select(appVersion);
        //List<AppVersion> appVersions = mapper.selectAll();//自动分页
        PageInfo<AppVersion> pageInfo = new PageInfo<>(appVersions);//生成分页对象
        return pageInfo;
    }

}
