package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TVisitor;

public interface TVisitorMapper {
    int deleteByPrimaryKey(String uid);

    int insert(TVisitor record);

    TVisitor selectByPrimaryKey(String uid);

    List<TVisitor> selectAll();

    int updateByPrimaryKey(TVisitor record);
}