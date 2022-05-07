package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TBlogSort;

public interface TBlogSortMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TBlogSort record);

    TBlogSort selectByPrimaryKey(String uid);

    List<TBlogSort> selectAll();

    int updateByPrimaryKey(TBlogSort record);
}