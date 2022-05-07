package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbRoleMenu;

public interface TbRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRoleMenu record);

    TbRoleMenu selectByPrimaryKey(Integer id);

    List<TbRoleMenu> selectAll();

    int updateByPrimaryKey(TbRoleMenu record);
}