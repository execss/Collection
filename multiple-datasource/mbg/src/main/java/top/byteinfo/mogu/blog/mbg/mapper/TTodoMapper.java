package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TTodo;

public interface TTodoMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TTodo record);

    TTodo selectByPrimaryKey(String uid);

    List<TTodo> selectAll();

    int updateByPrimaryKey(TTodo record);
}