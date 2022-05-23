package com.example.user.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleMenu {
    private Integer roleId;

    private Integer menuId;
}