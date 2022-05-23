package com.example.user.mbg.mapper;

import com.example.user.mbg.entity.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);
}