package com.example.sqltest.mbg.mapper;

import com.example.sqltest.mbg.entity.Student;
import java.util.List;

public interface StudentMapper {
    int insert(Student record);

    List<Student> selectAll();
}