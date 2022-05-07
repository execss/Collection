package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbChatRecord;

public interface TbChatRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbChatRecord record);

    TbChatRecord selectByPrimaryKey(Integer id);

    List<TbChatRecord> selectAll();

    int updateByPrimaryKey(TbChatRecord record);
}