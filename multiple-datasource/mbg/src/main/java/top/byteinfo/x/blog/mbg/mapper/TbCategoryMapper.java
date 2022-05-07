package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbCategory;

public interface TbCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCategory record);

    TbCategory selectByPrimaryKey(Integer id);

    List<TbCategory> selectAll();

    int updateByPrimaryKey(TbCategory record);
}