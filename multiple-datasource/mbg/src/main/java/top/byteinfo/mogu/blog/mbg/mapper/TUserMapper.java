package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TUser;

public interface TUserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TUser record);

    TUser selectByPrimaryKey(String uid);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);
}