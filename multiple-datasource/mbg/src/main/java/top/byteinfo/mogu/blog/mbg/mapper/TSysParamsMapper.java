package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TSysParams;

public interface TSysParamsMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TSysParams record);

    TSysParams selectByPrimaryKey(String uid);

    List<TSysParams> selectAll();

    int updateByPrimaryKey(TSysParams record);
}