package com.example.multipledatasource.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class HDataSourceConfig {
    static HikariConfig config = null;

    {
        config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    //    @Bean
////    @ConfigurationProperties("spring.datasource.hikari.h1")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.h1")
//    public HikariConfig hikariConfig1() {
//        return new HikariConfig();
//    }
    @Primary
    @Bean(name = "H1")
    @ConfigurationProperties("spring.datasource.hikari.h1")
    public DataSource first() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://192.168.1.11/cd_blog?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return hikariDataSource;
//        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "H2")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.h2")
    public DataSource second(DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://192.168.1.11/mogu_blog")
                .username("root")
                .password("root")
                .build();
//        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "H3")
    @ConfigurationProperties("spring.datasource.hikari.h3")
    public DataSource third() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://192.168.1.11/blog")
                .username("root")
                .password("root")
                .build();
//        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "HP")
    @ConfigurationProperties("spring.datasource.hikari.hp")
    public DataSource forth() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://192.168.1.11:5432/db1")
                .username("postgres")
                .password("root")
                .build();
//        return DruidDataSourceBuilder.create().build();
    }


}
