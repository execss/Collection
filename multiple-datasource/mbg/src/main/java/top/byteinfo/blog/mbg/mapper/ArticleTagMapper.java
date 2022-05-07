package top.byteinfo.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.blog.mbg.entity.ArticleTag;

public interface ArticleTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleTag record);

    ArticleTag selectByPrimaryKey(Integer id);

    List<ArticleTag> selectAll();

    int updateByPrimaryKey(ArticleTag record);
}