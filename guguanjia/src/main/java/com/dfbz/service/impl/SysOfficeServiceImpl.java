package com.dfbz.service.impl;

import com.dfbz.dao.SysOfficeMapper;
import com.dfbz.entity.SysOffice;
import com.dfbz.service.IService;
import com.dfbz.service.SysOfficeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author hjt
 * @description
 * @date 2019/11/20
 */
@CacheConfig(cacheNames = "officeCache")
@Service
@Transactional
public class SysOfficeServiceImpl extends ServiceImpl<SysOffice> implements SysOfficeService {

    @Autowired
    SysOfficeMapper mapper;

    /*@Autowired
    RedisTemplate<String,Object> redisTemplate;
*/

    @Override
    public PageInfo<SysOffice> selectByCondition(Map<String, Object> params) {

        if (StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }


        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        List<SysOffice> sysOffices = mapper.selectByCondition(params);
        PageInfo<SysOffice> sysOfficePageInfo = new PageInfo<>(sysOffices);
        return sysOfficePageInfo;
    }

    @Cacheable(key = "'SysOfficeServiceImpl:selectByOid:'+#oid")
    @Override
    public SysOffice selectByOid(long oid) {
        return mapper.selectByOid(oid);
    }


    /**
     * 使用redis作为缓存，提升热点查询方法的性能和体验
     * 1.从redis中查询是否存在该数据，redis中存在则直接从redis获取
     * 2.redis中不存在，则需要从数据库查询数据，并且返回的数据需要放入redis中
     *
     * key:   SysOfficeServiceImpl:selectAll    类名:方法名:参数列表
     *
     */
    /*@Override
    public List<SysOffice> selectAll() {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        if (redisTemplate.hasKey("SysOfficeServiceImpl:selectAll")){
            return (List<SysOffice>)opsForValue.get("SysOfficeServiceImpl:selectAll");
        }
        List<SysOffice> sysOffices = super.selectAll();
        opsForValue.set("SysOfficeServiceImpl:selectAll",sysOffices);

        return sysOffices;
    }*/

    @Cacheable(key ="'SysOfficeServiceImpl:selectAll'")
    @Override
    public List<SysOffice> selectAll() {

        return super.selectAll();
    }

    @CacheEvict(allEntries = true)
    @Override
    public int updateByPrimaryKey(SysOffice sysOffice) {
        return super.updateByPrimaryKey(sysOffice);
    }
}
