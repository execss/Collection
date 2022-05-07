package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TSubjectItem;

public interface TSubjectItemMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TSubjectItem record);

    TSubjectItem selectByPrimaryKey(String uid);

    List<TSubjectItem> selectAll();

    int updateByPrimaryKey(TSubjectItem record);
}