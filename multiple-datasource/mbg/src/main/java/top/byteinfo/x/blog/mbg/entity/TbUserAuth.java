package top.byteinfo.x.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbUserAuth {
    private Integer id;

    private Integer userInfoId;

    private String username;

    private String password;

    private Boolean loginType;

    private String ipAddress;

    private String ipSource;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;
}