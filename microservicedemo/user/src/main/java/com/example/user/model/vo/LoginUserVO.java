package com.example.user.model.vo;

import com.example.user.mbg.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO {
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
    private List<Role> roles;
    private String roleId;
    private String oldPassword;
    private String newPassword;
    private Set<String> permissions;
    private String userId;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean del;

}
