package top.byteinfo.mogu.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TSysLog {
    private String uid;

    private String userName;

    private String adminUid;

    private String ip;

    private String url;

    private String type;

    private String classPath;

    private String method;

    private String operation;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String ipSource;

    private Integer spendTime;

    private String params;
}