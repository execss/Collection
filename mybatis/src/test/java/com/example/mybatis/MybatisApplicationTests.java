package com.example.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.byteinfo.blog.mbg.mapper.ArticleTagMapper;

import javax.annotation.Resource;

@SpringBootTest
class MybatisApplicationTests {

    @Resource
    ArticleTagMapper articleTagMapper;
    @Test
    void contextLoads() {
        articleTagMapper.selectAll();
    }

}
