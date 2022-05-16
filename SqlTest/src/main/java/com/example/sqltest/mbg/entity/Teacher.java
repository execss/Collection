package com.example.sqltest.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Teacher {
    private String tid;

    private String tname;
}