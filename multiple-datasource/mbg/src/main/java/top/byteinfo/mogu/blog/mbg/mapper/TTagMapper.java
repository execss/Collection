package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TTag;

public interface TTagMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TTag record);

    TTag selectByPrimaryKey(String uid);

    List<TTag> selectAll();

    int updateByPrimaryKey(TTag record);
}