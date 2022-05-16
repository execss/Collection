package com.example.sqltest.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.sqltest.mbg.mapper", sqlSessionTemplateRef = "h1SqlSessionTemplate")
public class H1 {

    @Bean(name = "SQL")
    public DataSource forth() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://192.168.1.11/Test")
                .username("root")
                .password("root")
                .build();
    }


    @Primary
    @Bean(name = "h1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("SQL") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean("h1TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("SQL") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("h1SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("h1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
