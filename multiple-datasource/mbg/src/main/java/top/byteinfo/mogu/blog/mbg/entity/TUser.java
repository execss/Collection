package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TUser {
    private String uid;

    private String userName;

    private String passWord;

    private Byte gender;

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

    private String source;

    private String uuid;

    private String qqNumber;

    private String weChat;

    private String occupation;

    private Boolean commentStatus;

    private String ipSource;

    private String browser;

    private String os;

    private Boolean startEmailNotification;

    private Boolean userTag;

    private Boolean loadingValid;
}