package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TComment;

public interface TCommentMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TComment record);

    TComment selectByPrimaryKey(String uid);

    List<TComment> selectAll();

    int updateByPrimaryKey(TComment record);
}