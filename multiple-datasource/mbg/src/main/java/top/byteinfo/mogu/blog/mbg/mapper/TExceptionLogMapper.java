package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TExceptionLog;

public interface TExceptionLogMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TExceptionLog record);

    TExceptionLog selectByPrimaryKey(String uid);

    List<TExceptionLog> selectAll();

    int updateByPrimaryKey(TExceptionLog record);
}