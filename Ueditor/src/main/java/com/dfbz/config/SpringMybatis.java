package com.dfbz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.mapper.session.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
/**
 * @author hjt
 * @description
 * @date 2019/11/12
 */
@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "com.dfbz.dao")
@ComponentScan(basePackages = {"com.dfbz.service","com.dfbz.utils"})
@EnableTransactionManagement
@PropertySource(value = "classpath:sys.properties",encoding = "utf-8")
public class SpringMybatis {
    @Bean
    public DataSource getDataSource(){
        Properties properties = new Properties();
        try {
            properties.load(SpringMybatis.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(properties);//自动配置
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory getFactory(DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);

        //设置分页插件   分页拦截器,类似过滤器
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(new Properties());//调用设置属性方法，进行方言设置
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        //设置驼峰命名转换
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);


        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public DataSourceTransactionManager getTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
