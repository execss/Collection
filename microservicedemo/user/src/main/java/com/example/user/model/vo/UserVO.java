package com.example.user.model.vo;

import com.example.user.mbg.entity.Role;
import com.example.user.model.dto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private Long id;
    private Date createTime;
    private Date updateTime;
    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String mobile;
    private Integer sex;
    private Boolean enabled;
    private String type;
    private String openId;
    private List<RoleDTO> roleDTOs;
    private String roleId;
    private String oldPassword;
    private String newPassword;
    private Boolean del;
}
