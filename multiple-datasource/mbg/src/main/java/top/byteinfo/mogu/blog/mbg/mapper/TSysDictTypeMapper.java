package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TSysDictType;

public interface TSysDictTypeMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TSysDictType record);

    TSysDictType selectByPrimaryKey(String uid);

    List<TSysDictType> selectAll();

    int updateByPrimaryKey(TSysDictType record);
}