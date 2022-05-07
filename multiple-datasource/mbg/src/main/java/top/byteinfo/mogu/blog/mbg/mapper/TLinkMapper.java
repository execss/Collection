package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TLink;

public interface TLinkMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TLink record);

    TLink selectByPrimaryKey(String uid);

    List<TLink> selectAll();

    int updateByPrimaryKey(TLink record);
}