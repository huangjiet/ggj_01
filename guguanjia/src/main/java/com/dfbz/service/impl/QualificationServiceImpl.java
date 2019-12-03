package com.dfbz.service.impl;

import com.dfbz.dao.QualificationMapper;
import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.Qualification;
import com.dfbz.entity.SysUser;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/11/16
 *
 *
 * params: pageNum,pageSize,type,check,begin,end
 */

@Service
@Transactional
public class QualificationServiceImpl extends ServiceImpl<Qualification> implements QualificationService {

    @Autowired
    SysUserMapper userMapper;

    @Value("${imgPath}")
    String imgPath;

    @Override
    public PageInfo<Qualification> selectByCondition(Map<String, Object> params) {
        //默认值
        if (StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));

        //拼接sql

        Example example = new Example(Qualification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag","0");
        if (!StringUtils.isEmpty(params.get("type"))){
            criteria.andEqualTo("type",params.get("type"));
        }
        if (!StringUtils.isEmpty(params.get("check"))) {
            criteria.andEqualTo("check",params.get("check"));
        }
        if (!StringUtils.isEmpty(params.get("begin"))){
            criteria.andGreaterThan("createDate",params.get("begin"));
        }
        if (!StringUtils.isEmpty(params.get("end"))){
            criteria.andLessThan("createDate",params.get("end"));
        }
        List<Qualification> qualifications = mapper.selectByExample(example);

        QualificationMapper qualificationMapper= (QualificationMapper)mapper;

        //根据qualifications中的uploadUserId和checkUserId查询数据

        for (Qualification qualification : qualifications){
            Map<String,Object> names=qualificationMapper.selectNames(qualification.getId());
            qualification.setCheckUserName((String) names.get("checkUserName"));
            qualification.setUploadUserName((String)names.get("uploadUserName"));
        }

        return new PageInfo<Qualification>(qualifications);
    }

    @Override
    public Qualification selectByPrimaryKey(Object key) {

        Qualification qualification = mapper.selectByPrimaryKey(key);
        SysUser sysUser = userMapper.selectByPrimaryKey(qualification.getCheckUserId());
        qualification.setAddress(imgPath+sysUser.getOfficeId()+ File.separator+qualification.getAddress());
        return qualification;
    }
}
