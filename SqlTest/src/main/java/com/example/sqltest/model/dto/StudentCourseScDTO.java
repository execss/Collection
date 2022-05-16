package com.example.sqltest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StudentCourseScDTO {
    private String sid;
    private List<CourseScDTO> courseScDTOList;
}
