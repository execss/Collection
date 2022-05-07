package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TRole;

public interface TRoleMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TRole record);

    TRole selectByPrimaryKey(String uid);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);
}