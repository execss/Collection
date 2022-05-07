package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbUniqueView;

public interface TbUniqueViewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUniqueView record);

    TbUniqueView selectByPrimaryKey(Integer id);

    List<TbUniqueView> selectAll();

    int updateByPrimaryKey(TbUniqueView record);
}