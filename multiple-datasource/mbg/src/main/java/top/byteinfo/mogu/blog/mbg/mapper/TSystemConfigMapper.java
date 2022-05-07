package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TSystemConfig;

public interface TSystemConfigMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TSystemConfig record);

    TSystemConfig selectByPrimaryKey(String uid);

    List<TSystemConfig> selectAll();

    int updateByPrimaryKey(TSystemConfig record);
}