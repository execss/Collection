package com.example.sqltest.mbg.mapper;

import com.example.sqltest.mbg.entity.Teacher;
import java.util.List;

public interface TeacherMapper {
    int insert(Teacher record);

    List<Teacher> selectAll();
}