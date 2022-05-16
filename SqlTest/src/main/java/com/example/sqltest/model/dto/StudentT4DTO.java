package com.example.sqltest.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentT4DTO {
    private String sid;
    private String name;
    private Integer courseCount;
    private Integer scSum;
}
