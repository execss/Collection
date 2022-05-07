package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TStudyVideo;

public interface TStudyVideoMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TStudyVideo record);

    TStudyVideo selectByPrimaryKey(String uid);

    List<TStudyVideo> selectAll();

    int updateByPrimaryKey(TStudyVideo record);
}