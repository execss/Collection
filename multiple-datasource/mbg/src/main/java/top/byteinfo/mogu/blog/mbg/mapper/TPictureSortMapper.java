package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TPictureSort;

public interface TPictureSortMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TPictureSort record);

    TPictureSort selectByPrimaryKey(String uid);

    List<TPictureSort> selectAll();

    int updateByPrimaryKey(TPictureSort record);
}