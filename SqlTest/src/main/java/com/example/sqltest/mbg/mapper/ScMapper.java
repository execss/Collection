package com.example.sqltest.mbg.mapper;

import com.example.sqltest.mbg.entity.Sc;
import java.util.List;

public interface ScMapper {
    int insert(Sc record);

    List<Sc> selectAll();
}