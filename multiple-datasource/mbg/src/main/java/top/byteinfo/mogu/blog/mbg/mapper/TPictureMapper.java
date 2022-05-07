package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TPicture;

public interface TPictureMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TPicture record);

    TPicture selectByPrimaryKey(String uid);

    List<TPicture> selectAll();

    int updateByPrimaryKey(TPicture record);
}