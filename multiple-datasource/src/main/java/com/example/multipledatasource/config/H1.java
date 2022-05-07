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
@MapperScan(basePackages = "top.byteinfo.blog.mbg.mapper", sqlSessionTemplateRef = "h1SqlSessionTemplate")
public class H1 {

    @Primary
    @Bean(name = "h1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("H1") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/dcp1/*.xml"));
        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean("h1TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("H1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("h1SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("h1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
