package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbRole;

public interface TbRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRole record);

    TbRole selectByPrimaryKey(Integer id);

    List<TbRole> selectAll();

    int updateByPrimaryKey(TbRole record);
}