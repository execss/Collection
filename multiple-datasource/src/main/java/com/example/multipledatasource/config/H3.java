package com.example.multipledatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "top.byteinfo.x.blog.mbg.mapper", sqlSessionTemplateRef = "h3SqlSessionTemplate")
public class H3 {

    @Primary
    @Bean(name = "h3SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("H3") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:mapper/dcp3/*.xml");

        sessionFactoryBean.setMapperLocations(resources);
        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean("h3TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("H3") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("h3SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("h3SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
