package com.example.user.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Menu {
    private Integer id;

    private Integer parentId;

    private String name;

    private String url;

    private String path;

    private String pathMethod;

    private String css;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

    private Boolean type;

    private Boolean hidden;

    private String tenantId;
}