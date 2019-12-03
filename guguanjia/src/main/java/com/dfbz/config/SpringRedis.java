package com.dfbz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author hjt
 * @description
 * @date 2019/11/26
 */

@Configuration
@PropertySource(encoding = "utf-8",value = "classpath:redis.properties")
public class SpringRedis {
    @Value("${host}")
    public String host;
    @Value("${port}")
    public int port;
    @Value("${password}")
    public String password;
    @Value("${maxIdle}")
    public int maxIdle;
    @Value("${minIdle}")
    public int minIdle;



    //1.配置连接redis数据源
    @Bean
    public RedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(host);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);


        //设置连接池配置对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        connectionFactory.setPoolConfig(poolConfig);

        return connectionFactory;

    }



    /*
    * 配置RedisTemplate的key和value的序列化策略
    * 为了方便管理数据库，在数据库中也可以看到key和value数据的结构，需要自定义序列化策略
    * */
    @Bean
    public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory connectionFactory){

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(connectionFactory);
        //设置key为非hash的序列化和反序列化策略
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());


        //设置value为非hash类型的序列化和反序列化策略
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);



        redisTemplate.setHashKeySerializer(redisTemplate.getHashValueSerializer());
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        return redisTemplate;


    }





}
