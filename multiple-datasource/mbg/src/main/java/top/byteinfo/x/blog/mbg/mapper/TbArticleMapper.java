package top.byteinfo.x.blog.mbg.mapper;

import top.byteinfo.x.blog.mbg.entity.TbArticle;

import java.util.List;

public interface TbArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbArticle record);

    TbArticle selectByPrimaryKey(Integer id);

    List<TbArticle> selectAll();

    int updateByPrimaryKey(TbArticle record);
}