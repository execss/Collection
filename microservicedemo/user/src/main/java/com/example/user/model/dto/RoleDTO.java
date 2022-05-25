package com.example.user.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDTO {
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private String code;
    private String name;
    private Long userId;
}
