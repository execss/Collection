package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TCategoryMenu;

public interface TCategoryMenuMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TCategoryMenu record);

    TCategoryMenu selectByPrimaryKey(String uid);

    List<TCategoryMenu> selectAll();

    int updateByPrimaryKey(TCategoryMenu record);
}