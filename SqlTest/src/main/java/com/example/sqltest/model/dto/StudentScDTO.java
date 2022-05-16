package com.example.sqltest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StudentScDTO {
    private String sid;
    private Integer aveSc;
    private List<CourseScDTO> courseScDTOList;
}
