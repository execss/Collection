package com.example.sqltest.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course {
    private String cid;

    private String cname;

    private String tid;
}