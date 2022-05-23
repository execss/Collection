package com.example.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuVOT {

    private Long id;
    private Date createTime;
    private Date updateTime;
    private Long parentId;
    private String name;
    private String css;
    private String url;
    private String path;
    private Integer sort;
    private Integer type;
    private Boolean hidden;

    private String pathMethod;
    private List<MenuVOT> subMenus;
    private String roleId;
    private String menuIds;

    private String test;


}
