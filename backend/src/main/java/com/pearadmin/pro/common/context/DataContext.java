package com.pearadmin.pro.common.context;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.pearadmin.pro.modules.sys.domain.SysDataSource;
import com.pearadmin.pro.modules.sys.param.SysDataSourceRequest;
import com.pearadmin.pro.modules.sys.service.SysDataSourceService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Data Context 数 据 源 上 下 文
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/10/23
 * */
@Component
public class DataContext {

    @Resource
    protected DataSource dataSource;

    @Resource
    protected DataSourceCreator dataSourceCreator;

    @Resource
    private SysDataSourceService sysDataSourceService;

    /**
     * 新增数据源
     * */
    public void createDataSource(String name, String username, String password, String url, String driver){
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) dataSource;

        DataSourceProperty dsp = new DataSourceProperty();
        dsp.setPoolName(name);
        dsp.setUrl(url);
        dsp.setUsername(username);
        dsp.setPassword(password);
        dsp.setDriverClassName(driver);

        DataSource dataSource = dataSourceCreator.createDataSource(dsp);
        dynamicRoutingDataSource.addDataSource(name, dataSource);
    }

    /**
     * 修改数据源
     * */
    public void updateDataSource(String name, String username, String password, String url, String driver) {
        removeDataSource(name);
        createDataSource(name, username, password, url, driver);
    }

    /**
     * 删除数据源
     * */
    public void removeDataSource(String name){
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) dataSource;
        dynamicRoutingDataSource.removeDataSource(name);
    }

    /// 初 始 化 ...
    @PostConstruct
    public void loadDataSource(){
       List<SysDataSource> sysDataSources = sysDataSourceService.list(new SysDataSourceRequest());
       sysDataSources.forEach(sysDataSource -> {
           createDataSource(
                   sysDataSource.getCode(),
                   sysDataSource.getUsername(),
                   sysDataSource.getPassword(),
                   sysDataSource.getUrl(),
                   sysDataSource.getDriver());
       });
    }
}
