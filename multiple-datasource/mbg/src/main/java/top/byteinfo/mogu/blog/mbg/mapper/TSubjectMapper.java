package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TSubject;

public interface TSubjectMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TSubject record);

    TSubject selectByPrimaryKey(String uid);

    List<TSubject> selectAll();

    int updateByPrimaryKey(TSubject record);
}