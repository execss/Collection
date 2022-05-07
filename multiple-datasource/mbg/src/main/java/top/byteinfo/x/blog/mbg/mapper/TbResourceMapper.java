package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbResource;

public interface TbResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbResource record);

    TbResource selectByPrimaryKey(Integer id);

    List<TbResource> selectAll();

    int updateByPrimaryKey(TbResource record);
}