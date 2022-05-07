package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TSysLog;

public interface TSysLogMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TSysLog record);

    TSysLog selectByPrimaryKey(String uid);

    List<TSysLog> selectAll();

    int updateByPrimaryKey(TSysLog record);
}