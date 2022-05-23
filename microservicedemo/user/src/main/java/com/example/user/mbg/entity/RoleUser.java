package com.example.user.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleUser {
    private Integer userId;

    private Integer roleId;
}