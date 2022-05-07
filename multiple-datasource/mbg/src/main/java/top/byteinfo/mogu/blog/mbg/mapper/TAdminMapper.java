package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TAdmin;

public interface TAdminMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TAdmin record);

    TAdmin selectByPrimaryKey(String uid);

    List<TAdmin> selectAll();

    int updateByPrimaryKey(TAdmin record);
}