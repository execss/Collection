//package com.example.multipledatasource.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "top.byteinfo.x.blog.mbg.mapper", sqlSessionTemplateRef = "druid3SqlSessionTemplate")
//public class Druid3 {
//
//    @Primary
//    @Bean(name = "druid3SqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("Druid3") DataSource dataSource) throws Exception {
//
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:mapper/druid3/*.xml");
//
//        sessionFactoryBean.setMapperLocations(resources);
//        return sessionFactoryBean.getObject();
//    }
//
//    @Primary
//    @Bean("druid3TransactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("Druid3") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Primary
//    @Bean("druid3SqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("druid3SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
