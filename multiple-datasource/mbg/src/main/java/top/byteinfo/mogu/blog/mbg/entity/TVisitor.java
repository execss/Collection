package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TVisitor {
    private String uid;

    private String userName;

    private String email;

    private Integer loginCount;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Byte status;

    private Date createTime;

    private Date updateTime;
}