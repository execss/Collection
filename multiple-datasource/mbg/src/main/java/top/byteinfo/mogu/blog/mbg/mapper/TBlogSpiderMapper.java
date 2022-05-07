package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TBlogSpider;

public interface TBlogSpiderMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TBlogSpider record);

    TBlogSpider selectByPrimaryKey(String uid);

    List<TBlogSpider> selectAll();

    int updateByPrimaryKey(TBlogSpider record);
}