package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TCollect;

public interface TCollectMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TCollect record);

    TCollect selectByPrimaryKey(String uid);

    List<TCollect> selectAll();

    int updateByPrimaryKey(TCollect record);
}