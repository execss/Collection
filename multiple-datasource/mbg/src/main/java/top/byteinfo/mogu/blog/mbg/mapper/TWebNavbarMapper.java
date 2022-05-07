package top.byteinfo.mogu.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.mogu.blog.mbg.entity.TWebNavbar;

public interface TWebNavbarMapper {
    int insert(TWebNavbar record);

    List<TWebNavbar> selectAll();
}