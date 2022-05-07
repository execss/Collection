package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TWebVisit;

public interface TWebVisitMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TWebVisit record);

    TWebVisit selectByPrimaryKey(String uid);

    List<TWebVisit> selectAll();

    int updateByPrimaryKey(TWebVisit record);
}