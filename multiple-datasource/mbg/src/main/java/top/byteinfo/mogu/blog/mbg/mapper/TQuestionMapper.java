package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.byteinfo.mogu.blog.mbg.entity.TQuestion;

public interface TQuestionMapper {
    int deleteByPrimaryKey(@Param("uid") String uid, @Param("oid") Integer oid);

    int insert(TQuestion record);

    TQuestion selectByPrimaryKey(@Param("uid") String uid, @Param("oid") Integer oid);

    List<TQuestion> selectAll();

    int updateByPrimaryKey(TQuestion record);
}