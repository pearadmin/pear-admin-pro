package com.pearadmin.pro.common.web.interceptor;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import javax.annotation.Resource;
import java.util.List;

@Configuration
public class DataScopeConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.addMyInterceptor();
    }

    private void addMyInterceptor() {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(new DataScopeInterceptor());
        }
    }
}
