package com.example.sqltest.mbg.mapper;

import com.example.sqltest.mbg.entity.Course;
import java.util.List;

public interface CourseMapper {
    int insert(Course record);

    List<Course> selectAll();
}