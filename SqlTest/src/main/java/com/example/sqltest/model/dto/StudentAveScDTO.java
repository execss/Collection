package com.example.sqltest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentAveScDTO {
    private String sid;
    private String name;
    private Integer aveSc;
}
