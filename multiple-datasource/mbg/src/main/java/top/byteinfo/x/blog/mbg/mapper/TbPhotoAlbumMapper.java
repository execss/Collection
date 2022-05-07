package top.byteinfo.x.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.x.blog.mbg.entity.TbPhotoAlbum;

public interface TbPhotoAlbumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPhotoAlbum record);

    TbPhotoAlbum selectByPrimaryKey(Integer id);

    List<TbPhotoAlbum> selectAll();

    int updateByPrimaryKey(TbPhotoAlbum record);
}