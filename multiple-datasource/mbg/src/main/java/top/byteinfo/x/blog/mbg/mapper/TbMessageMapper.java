package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbMessage;

public interface TbMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbMessage record);

    TbMessage selectByPrimaryKey(Integer id);

    List<TbMessage> selectAll();

    int updateByPrimaryKey(TbMessage record);
}