package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbOperationLog;

public interface TbOperationLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbOperationLog record);

    TbOperationLog selectByPrimaryKey(Integer id);

    List<TbOperationLog> selectAll();

    int updateByPrimaryKey(TbOperationLog record);
}