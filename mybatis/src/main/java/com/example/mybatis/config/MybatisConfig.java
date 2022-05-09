package com.example.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("top.byteinfo.blog.mbg.mapper")
public class MybatisConfig {
}
