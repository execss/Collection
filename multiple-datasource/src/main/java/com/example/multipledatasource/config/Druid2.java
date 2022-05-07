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
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "top.byteinfo.mogu.blog.mbg.mapper", sqlSessionTemplateRef = "druid2SqlSessionTemplate")
//public class Druid2 {
//
//    @Primary
//    @Bean(name = "druid2SqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("Druid2") DataSource dataSource) throws Exception {
//
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/druid2/*Mapper.xml"));
//        return sessionFactoryBean.getObject();
//    }
//
//    @Primary
//    @Bean("druid2TransactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("Druid2") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Primary
//    @Bean("druid2SqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("druid2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
