package com.example.sqltest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class StudentScDetailDTO {
    private String sid;
    private Integer aveSc;
    private Map courseScMap;
}
