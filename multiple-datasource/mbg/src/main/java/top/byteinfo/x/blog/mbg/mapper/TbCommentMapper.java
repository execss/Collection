package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbComment;

public interface TbCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbComment record);

    TbComment selectByPrimaryKey(Integer id);

    List<TbComment> selectAll();

    int updateByPrimaryKey(TbComment record);
}