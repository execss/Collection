package com.example.user.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private Integer id;

    private String code;

    private String name;

    private Date createTime;

    private Date updateTime;

    private String tenantId;
}