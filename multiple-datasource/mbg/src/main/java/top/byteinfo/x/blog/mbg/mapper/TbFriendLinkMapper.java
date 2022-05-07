package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbFriendLink;

public interface TbFriendLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFriendLink record);

    TbFriendLink selectByPrimaryKey(Integer id);

    List<TbFriendLink> selectAll();

    int updateByPrimaryKey(TbFriendLink record);
}