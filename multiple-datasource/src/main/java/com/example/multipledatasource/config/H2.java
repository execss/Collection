package com.example.multipledatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "top.byteinfo.mogu.blog.mbg.mapper", sqlSessionTemplateRef = "h2SqlSessionTemplate")
public class H2 {

    @Primary
    @Bean(name = "h2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("H2") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/dcp2/*Mapper.xml"));
        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean("h2TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("H2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("h2SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("h2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
