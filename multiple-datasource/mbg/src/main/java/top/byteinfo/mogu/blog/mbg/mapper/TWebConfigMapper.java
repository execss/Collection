package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TWebConfig;

public interface TWebConfigMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TWebConfig record);

    TWebConfig selectByPrimaryKey(String uid);

    List<TWebConfig> selectAll();

    int updateByPrimaryKey(TWebConfig record);
}