package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TFeedback;

public interface TFeedbackMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TFeedback record);

    TFeedback selectByPrimaryKey(String uid);

    List<TFeedback> selectAll();

    int updateByPrimaryKey(TFeedback record);
}