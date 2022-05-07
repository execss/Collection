package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TSysDictData;

public interface TSysDictDataMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TSysDictData record);

    TSysDictData selectByPrimaryKey(String uid);

    List<TSysDictData> selectAll();

    int updateByPrimaryKey(TSysDictData record);
}