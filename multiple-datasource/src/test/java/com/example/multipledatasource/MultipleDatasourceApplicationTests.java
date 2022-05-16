package com.example.multipledatasource;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.byteinfo.blog.mbg.mapper.ArticleTagMapper;
import top.byteinfo.mogu.blog.mbg.mapper.TBlogMapper;
import top.byteinfo.x.blog.mbg.mapper.TbArticleMapper;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@SpringBootTest
class MultipleDatasourceApplicationTests {

    @Resource
    private ArticleTagMapper articleTagMapper;
    @Resource
    private TBlogMapper tBlogMapper;
    @Resource
    private TbArticleMapper tbArticleMapper;
    @Test
    void contextLoads() {
        long l = System.currentTimeMillis();

        log.info(""+l);
        log.info(new Date(l).toString());
        Long ll =(2022L-1970)*365*24*60*60*1000;
        long l1 = ll / l;
        log.info(ll+"");
        log.info(System.currentTimeMillis()+"");

//        log.info(Jackson.toString(articleTagMapper.selectAll()));
//        log.info(Jackson.toString(tBlogMapper.selectAll()));
//        log.info(Jackson.toString(tbArticleMapper.selectAll()));
    }

}
