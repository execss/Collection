package com.example.sqltest.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private String sid;

    private String sname;

    private Date sage;

    private String ssex;
}