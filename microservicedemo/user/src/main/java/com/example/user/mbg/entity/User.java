package com.example.user.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String headImgUrl;

    private String mobile;

    private Integer sex;

    private Boolean enabled;

    private String type;

    private Date createTime;

    private Date updateTime;

    private String company;

    private String openId;

    private Boolean isDel;
}