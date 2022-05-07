package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TAdmin {
    private String uid;

    private String userName;

    private String passWord;

    private String gender;

    private String avatar;

    private String email;

    private Date birthday;

    private String mobile;

    private String validCode;

    private String summary;

    private Integer loginCount;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String nickName;

    private String qqNumber;

    private String weChat;

    private String occupation;

    private String github;

    private String gitee;

    private String roleUid;

    private String personResume;
}