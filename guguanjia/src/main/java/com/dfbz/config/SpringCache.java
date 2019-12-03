package com.dfbz.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;

/**
 * @author hjt
 * @description
 * @date 2019/11/26
 */
@Configuration
@EnableCaching
public class SpringCache {

    @Bean
    public CacheManager getCacheManager(RedisTemplate<String,Object> redisTemplate){

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        ArrayList<String> collection = new ArrayList<>();
        collection.add("officeCache");
        collection.add("resourceCache");
        redisCacheManager.setCacheNames(collection);
        redisCacheManager.setDefaultExpiration(1800);
        return redisCacheManager;

    }

}
