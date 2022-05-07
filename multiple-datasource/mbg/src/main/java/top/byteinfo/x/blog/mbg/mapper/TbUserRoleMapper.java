package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbUserRole;

public interface TbUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUserRole record);

    TbUserRole selectByPrimaryKey(Integer id);

    List<TbUserRole> selectAll();

    int updateByPrimaryKey(TbUserRole record);
}