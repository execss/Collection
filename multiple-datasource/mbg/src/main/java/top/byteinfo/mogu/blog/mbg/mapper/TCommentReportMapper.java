package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TCommentReport;

public interface TCommentReportMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TCommentReport record);

    TCommentReport selectByPrimaryKey(String uid);

    List<TCommentReport> selectAll();

    int updateByPrimaryKey(TCommentReport record);
}