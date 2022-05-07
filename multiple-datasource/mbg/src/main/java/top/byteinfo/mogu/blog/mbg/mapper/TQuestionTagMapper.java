package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TQuestionTag;

public interface TQuestionTagMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TQuestionTag record);

    TQuestionTag selectByPrimaryKey(String uid);

    List<TQuestionTag> selectAll();

    int updateByPrimaryKey(TQuestionTag record);
}