package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbPage;

public interface TbPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPage record);

    TbPage selectByPrimaryKey(Integer id);

    List<TbPage> selectAll();

    int updateByPrimaryKey(TbPage record);
}