package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TResourceSort;

public interface TResourceSortMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TResourceSort record);

    TResourceSort selectByPrimaryKey(String uid);

    List<TResourceSort> selectAll();

    int updateByPrimaryKey(TResourceSort record);
}