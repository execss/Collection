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
//@MapperScan(basePackages = "top.byteinfo.blog.mbg.mapper", sqlSessionTemplateRef = "druid1SqlSessionTemplate")
//public class Druid1 {
//
//    @Primary
//    @Bean(name = "druid1SqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("Druid1") DataSource dataSource) throws Exception {
//
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/druid1/*.xml"));
//        return sessionFactoryBean.getObject();
//    }
//
//    @Primary
//    @Bean("druid1TransactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("Druid1") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Primary
//    @Bean("druid1SqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("druid1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
