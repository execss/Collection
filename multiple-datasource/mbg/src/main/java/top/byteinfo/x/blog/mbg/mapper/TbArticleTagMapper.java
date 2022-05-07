package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbArticleTag;

public interface TbArticleTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbArticleTag record);

    TbArticleTag selectByPrimaryKey(Integer id);

    List<TbArticleTag> selectAll();

    int updateByPrimaryKey(TbArticleTag record);
}